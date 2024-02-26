package com.ingenious.data.user.details

import com.ingenious.boundary.user.details.UserDetails
import com.ingenious.data.user.UserDetailsDto

internal fun UserDetailsDto.toUserDetails(): UserDetails {
    return UserDetails(
        id = this.id,
        userName = this.login,
        nodeId = this.nodeId,
        avatarUrl = this.avatarUrl,
        type = this.type,
        name = this.name,
        company = this.company,
        email = this.email,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}