dependencies {
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}

// Use um número inteiro para a versão
version = 1

cloudstream {
    // Nome do plugin que vai aparecer no aplicativo
    name = "Meus Canais"
    description = "Minhas Extensoes"
    authors = listOf("LuisGodoi21")

    // Define quais classes o CloudStream deve carregar
    providerClassName = "com.example.KairouProvider,com.example.MinhaTelaProvider"

    // Configurações recomendadas para canais/filmes do Brasil
    status = 1 
    tvTypes = listOf("LiveStream", "Movie")
    language = "pt-BR"
}

android {
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}
