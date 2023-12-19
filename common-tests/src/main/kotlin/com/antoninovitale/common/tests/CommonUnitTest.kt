package com.antoninovitale.common.tests

import com.antoninovitale.beerpedia.common.IDispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

/**
 * Base class for unit tests, containing setup for mocks and testing suspend functions.
 */
abstract class CommonUnitTest<T : Any> {

    protected val dispatchers: IDispatcherProvider
        get() = TestDispatcherProvider(dispatcher)

    private val scope: TestScope
        get() = TestScope()

    private lateinit var mocksCloseable: AutoCloseable

    protected lateinit var sut: T

    abstract fun sut(): T

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        mocksCloseable = MockitoAnnotations.openMocks(this)

        Dispatchers.setMain(dispatcher)

        sut = sut()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        mocksCloseable.close()

        Dispatchers.resetMain()
    }

    protected fun runTest(testBody: suspend TestScope.() -> Unit) {
        scope.runTest(testBody = testBody)
    }
}
