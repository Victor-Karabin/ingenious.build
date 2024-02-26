package com.ingenious.data.user.details

import com.ingenious.boundary.UserDetailsFactory
import com.ingenious.common.MainCoroutineRule
import com.ingeniuos.common.requirePayload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class UserDetailsRepoTests {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val detailsFactory = UserDetailsFactory()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given cached data when details are requested then cache is used`() = runTest {
        // given cached data
        val details = detailsFactory.create(USERNAME)
        val cache = mock<UserDetailsStore> {
            onBlocking { get(USERNAME) } doReturn details
        }

        // when details are requested
        val repo = UserDetailsRepoImpl(
            usersApi = mock(),
            cache = cache,
            io = Dispatchers.IO
        )
        val result = repo.details(USERNAME)

        advanceUntilIdle()

        // then error emitted
        assertEquals(details, result.requirePayload())
    }

    companion object {
        private const val USERNAME = "username_1"
    }
}