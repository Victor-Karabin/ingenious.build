package com.ingenious.domain.user.details

import com.ingenious.boundary.user.details.UserDetails
import com.ingenious.interactors.user.details.UserWithDetails

internal fun UserDetails.toUserWithDetails(): UserWithDetails {
    return UserWithDetails(
        id = this.id,
        userName = this.userName,
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