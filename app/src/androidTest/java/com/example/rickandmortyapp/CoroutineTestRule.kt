package com.example.rickandmortyapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class CoroutineTestRule : TestRule {

    override fun apply(base: Statement?, description: Description?): Statement {
        return CoroutineRuleStatement(base)
    }

    inner class CoroutineRuleStatement(private val base: Statement?) : Statement() {

        private val testDispatcher = TestCoroutineDispatcher()

        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(testDispatcher)
            try {
                base?.evaluate()
            } finally {
                Dispatchers.resetMain()
                testDispatcher.cleanupTestCoroutines()
            }
        }
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