package com.ingenious.boundary

import com.ingenious.boundary.user.details.UserDetails
import kotlinx.datetime.LocalDateTime
import java.util.UUID

class UserDetailsFactory {

    fun create(userName: String): UserDetails {
        return UserDetails(
            id = 1,
            userName = userName,
            nodeId = UUID.randomUUID().toString(),
            avatarUrl = "http://avatar.jpg",
            type = "user",
            name = "Jonh Smith",
            company = "Com Inc",
            email = "jonh.smith@email.com",
            createdAt = LocalDateTime.parse("2008-01-14T04:33:35"),
            updatedAt = LocalDateTime.parse("2008-01-16T06:42:15"),
        )
    }
}