package com.ingenious.data.user.list

import com.ingenious.boundary.user.list.User
import com.ingenious.boundary.user.list.UsersPageRepo
import com.ingenious.data.backend.wrapRequest
import com.ingenious.data.user.UsersApi
import com.ingeniuos.common.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UsersPageRepoImpl @Inject constructor(
    private val usersApi: UsersApi,
    private val cache: UsersPageStore,
    @IoDispatcher
    private val io: CoroutineDispatcher
) : UsersPageRepo {

    override suspend fun loadPage(pageNum: Int, pageSize: Int): Result<List<User>> {
        val cached = cache.get(pageNum, pageSize)
        if (cached != null) return Result.success(cached)

        val since = cache.users().maxOfOrNull { user -> user.id } ?: 0

        return wrapRequest(io) { usersApi.listUsers(since, pageSize) }
            .map { users -> users.map { user -> user.toUser() } }
            .onSuccess { users -> cache.store(pageNum, pageSize, users) }
    }
}