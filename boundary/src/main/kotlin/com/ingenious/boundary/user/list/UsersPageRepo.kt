package com.ingenious.boundary.user.list

interface UsersPageRepo {

    suspend fun loadPage(pageNum: Int, pageSize: Int): Result<List<User>>
}