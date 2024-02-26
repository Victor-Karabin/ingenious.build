package com.ingenious.data.user.details

import com.ingenious.boundary.user.details.UserDetails

interface UserDetailsStore {

    suspend fun store(details: UserDetails)

    suspend fun get(userName: String): UserDetails?
}