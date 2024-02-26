package com.ingenious.data.user.list

import com.ingenious.boundary.user.list.User
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class UsersPageStoreImpl @Inject constructor() : UsersPageStore {

    private val mutex = Mutex()
    private val pages = HashMap<String, List<User>>()

    override suspend fun store(pageNum: Int, pageSize: Int, users: List<User>) {
        mutex.withLock {
            pages[key(pageNum, pageSize)] = users
        }
    }

    override suspend fun get(pageNum: Int, pageSize: Int): List<User>? {
        return pages[key(pageNum, pageSize)]
    }

    override suspend fun users(): List<User> {
        return pages.values.flatten()
    }

    private fun key(pageNum: Int, pageSize: Int): String {
        return "pageNum=$pageNum;pageSize=$pageSize"
    }
}