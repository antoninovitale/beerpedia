package com.antoninovitale.beerpedia.common

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {

    fun computation(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}
