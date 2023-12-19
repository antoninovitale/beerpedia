package com.antoninovitale.beerpedia.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProvider @Inject constructor(): IDispatcherProvider {

    override fun main(): CoroutineDispatcher =
        Dispatchers.Main

    override fun io(): CoroutineDispatcher =
        Dispatchers.IO

    override fun computation(): CoroutineDispatcher =
        Dispatchers.Default
}
