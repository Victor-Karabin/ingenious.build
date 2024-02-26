package com.ingenious.boundary.user.details

import com.ingenious.boundary.user.details.UserDetails

interface UserDetailsRepo {

    suspend fun details(username: String): Result<UserDetails>
}