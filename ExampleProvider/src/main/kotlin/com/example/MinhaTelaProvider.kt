package com.meurepo

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.ExtractorLink

class MinhaTelaProvider : MainAPI() {
    override var mainUrl = "https://minhatela.xyz"
    override var name = "MinhaTela"
    override val supportedTypes = setOf(TvType.Live)

    override suspend fun getMainPage(page: Int, request : MainPageRequest): HomePageResponse? {
        val document = app.get(mainUrl).document
        val liveEvents = ArrayList<SearchResponse>()
        
        document.select("a.evento, div.jogo").forEach { element ->
            val title = element.text()
            val url = element.attr("href")
            
            liveEvents.add(newLiveSearchResponse(title, url) {
                this.posterUrl = "https://minhatela.xyz" 
            })
        }
        return newHomePageResponse("Eventos Ao Vivo", liveEvents)
    }

    override suspend fun loadLinks(data: String, isCasting: Boolean, subtitleCallback: (SubtitleFile) -> Unit, callback: (ExtractorLink) -> Unit): Boolean {
        val document = app.get(data).document
        
        document.select("iframe, source").forEach { stream ->
            val streamUrl = stream.attr("src").ifEmpty { stream.attr("file") }
            if (streamUrl.isNotEmpty() && (streamUrl.contains("m3u8") || streamUrl.contains("embed"))) {
                callback.invoke(ExtractorLink(name, name, streamUrl, mainUrl, Qualities.Unknown.value, true))
            }
        }
        return true
    }
}
