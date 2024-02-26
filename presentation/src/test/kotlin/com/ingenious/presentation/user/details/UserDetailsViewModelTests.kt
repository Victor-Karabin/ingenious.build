package com.ingenious.build.presentation.user.details

import com.ingenious.common.MainCoroutineRule
import com.ingenious.interactors.user.details.UserDetailsUseCase
import com.ingenious.presentation.user.details.UserDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class UserDetailsViewModelTests {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given failed result when details are requested then error emitted`() = runTest {
        // given failed result
        val exception = IllegalStateException("user not found")
        val detailsUseCase = mock<UserDetailsUseCase> {
            onBlocking { invoke(any()) } doReturn Result.failure(exception)
        }

        // when details are requested
        val viewModel = UserDetailsViewModel(
            userName = USERNAME,
            detailsUseCase = detailsUseCase
        )

        advanceUntilIdle()

        // then error emitted
        assertEquals(exception, viewModel.error.firstOrNull())
    }

    companion object {
        private const val USERNAME = "username_1"
    }
}