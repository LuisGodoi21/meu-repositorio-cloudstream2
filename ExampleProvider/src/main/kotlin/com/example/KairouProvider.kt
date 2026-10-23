package com.meurepo

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.ExtractorLink

class KairouProvider : MainAPI() {
    override var mainUrl = "https://kairou.space"
    override var name = "Kairou"
    override val supportedTypes = setOf(TvType.TvSeries, TvType.Movie, TvType.Anime)

    override suspend fun getMainPage(page: Int, request : MainPageRequest): HomePageResponse? {
        val document = app.get(mainUrl).document
        val homeItems = ArrayList<SearchResponse>()
        
        document.select("div.item, article.vcard").forEach { element ->
            val title = element.selectFirst(".title, h2")?.text() ?: ""
            val url = element.selectFirst("a")?.attr("href") ?: ""
            val poster = element.selectFirst("img")?.attr("src") ?: ""
            
            homeItems.add(newMovieSearchResponse(title, url, TvType.Movie) {
                this.posterUrl = poster
            })
        }
        return newHomePageResponse(request.name, homeItems)
    }

    override suspend fun search(query: String): List<SearchResponse> {
        val searchUrl = "$mainUrl/?s=$query"
        val document = app.get(searchUrl).document
        return document.select("div.result-item").mapNotNull { element ->
            val title = element.selectFirst(".title")?.text() ?: return@mapNotNull null
            val url = element.selectFirst("a")?.attr("href") ?: ""
            val poster = element.selectFirst("img")?.attr("src") ?: ""
            newMovieSearchResponse(title, url, TvType.Movie) { this.posterUrl = poster }
        }
    }

    override suspend fun loadLinks(data: String, isCasting: Boolean, subtitleCallback: (SubtitleFile) -> Unit, callback: (ExtractorLink) -> Unit): Boolean {
        val document = app.get(data).document
        document.select("iframe, video source").forEach { server ->
            val videoUrl = server.attr("src")
            if (videoUrl.isNotEmpty()) {
                callback.invoke(ExtractorLink(name, name, videoUrl, mainUrl, Qualities.P1080.value, false))
            }
        }
        return true
    }
}
