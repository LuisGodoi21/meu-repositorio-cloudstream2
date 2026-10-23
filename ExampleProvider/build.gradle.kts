dependencies {
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}

// Use um número inteiro para a versão
version = 1

cloudstream {
    // CORRIGIDO: Removida a linha 'name = ' que causava erro de reatribuição

    description = "Minhas Extensoes"
    authors = listOf("LuisGodoi21")

    providerClass = "com.example.KairouProvider,com.example.MinhaTelaProvider"

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
