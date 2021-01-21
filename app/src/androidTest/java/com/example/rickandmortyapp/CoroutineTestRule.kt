package com.example.rickandmortyapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class CoroutineTestRule : TestRule {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                Dispatchers.setMain(testDispatcher)
                try {
                    base?.evaluate()
                } finally {
                    Dispatchers.resetMain()
                    testCoroutineScope.cleanupTestCoroutines()
                }
            }
        }
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
        testCoroutineScope.runBlockingTest { block() }
    }
}

/**
 *
 *   Test Order
 *
 *             One Time       One Time                      Each Test Method Run                     One Time      One Time
 *            ----------    ------------    -----------------------------------------------------   -----------   -----------
 *   Start -> @ClassRule -> @ClassBefore -> @get:Rule -> @Before -> @Test -> @After -> @get:Rule -> @ClassAfter -> @ClassRule -> End
 *
 */