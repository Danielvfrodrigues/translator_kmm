package br.com.codeforge.translator_kmm.translate.domain.translate

import br.com.codeforge.translator_kmm.core.domain.language.Language

interface TranslationClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String
}