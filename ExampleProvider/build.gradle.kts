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

    // CORRIGIDO: Alterado de "LiveStream" para "Live" para alinhar com o padrão do CloudStream
    status = 1 
    tvTypes = listOf("Live", "Movie")
    language = "pt-BR"
}

android {
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}
