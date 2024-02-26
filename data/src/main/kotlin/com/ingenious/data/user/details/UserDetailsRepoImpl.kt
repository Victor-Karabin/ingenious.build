package com.ingenious.data.user.details

import com.ingenious.boundary.user.details.UserDetails
import com.ingenious.boundary.user.details.UserDetailsRepo
import com.ingenious.data.backend.wrapRequest
import com.ingenious.data.user.UsersApi
import com.ingeniuos.common.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UserDetailsRepoImpl @Inject constructor(
    private val usersApi: UsersApi,
    private val cache: UserDetailsStore,
    @IoDispatcher
    private val io: CoroutineDispatcher
) : UserDetailsRepo {

    override suspend fun details(username: String): Result<UserDetails> {
        val cached = cache.get(username)
        if (cached != null) return Result.success(cached)

        return wrapRequest(io) { usersApi.getUser(username) }
            .map { user -> user.toUserDetails() }
            .onSuccess { details -> cache.store(details) }
    }
}