package com.ebayk.core.common

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProviderCore {

    fun bgDispatcher(): CoroutineDispatcher
    fun uiDispatcher(): CoroutineDispatcher
    fun ioDispatcher(): CoroutineDispatcher
    fun immediateDispatcher(): CoroutineDispatcher = uiDispatcher()
}