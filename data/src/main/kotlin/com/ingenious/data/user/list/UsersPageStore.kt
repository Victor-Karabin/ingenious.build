package com.ingenious.data.user.list

import com.ingenious.boundary.user.list.User

interface UsersPageStore {

    suspend fun store(pageNum: Int, pageSize: Int, users: List<User>)

    suspend fun get(pageNum: Int, pageSize: Int): List<User>?

    suspend fun users(): List<User>
}