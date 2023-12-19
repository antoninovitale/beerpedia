package com.antoninovitale.common.tests

import com.antoninovitale.beerpedia.common.IDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

class TestDispatcherProvider(
    private val dispatcher: TestDispatcher
) : IDispatcherProvider {

    override fun main(): CoroutineDispatcher =
        dispatcher

    override fun io(): CoroutineDispatcher =
        dispatcher

    override fun computation(): CoroutineDispatcher =
        dispatcher
}
