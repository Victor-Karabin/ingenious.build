package com.ingenious.data.user.list

import com.ingenious.boundary.user.list.User
import com.ingenious.data.user.UserDto

internal fun UserDto.toUser(): User {
    return User(
        id = this.id,
        userName = this.login,
        type = this.type,
        avatarUrl = this.avatarUrl
    )
}