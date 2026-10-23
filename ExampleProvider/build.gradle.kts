dependencies {
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}

// Use um número inteiro para a versão
version = 1

cloudstream {
    // Mantém a descrição e autores do seu repositório
    description = "Minhas Extensoes"
    authors = listOf("LuisGodoi21")

    // CORREÇÃO: Alinhado com o nome padrão da DSL do CloudStream e passado como lista
    providerClassName = listOf("com.example.KairouProvider", "com.example.MinhaTelaProvider")

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
