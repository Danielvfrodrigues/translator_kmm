package br.com.codeforge.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow

actual open class CommonMutableStateFlow<T> actual constructor(
    private val flow: MutableStateFlow<T>
): MutableStateFlow<T> by flow