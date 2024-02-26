package com.ingenious.data.user.details

import com.ingenious.boundary.user.details.UserDetails
import com.ingenious.data.user.details.UserDetailsStore
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class UserDetailsStoreImpl @Inject constructor() : UserDetailsStore {

    private val mutex = Mutex()
    private var details: UserDetails? = null

    override suspend fun store(details: UserDetails) {
        mutex.withLock { this.details = details }
    }

    override suspend fun get(userName: String): UserDetails? {
        return if (details?.userName == userName) details else null
    }
}