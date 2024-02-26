package com.ingenious.domain.user.list

import com.ingenious.boundary.user.list.User
import com.ingenious.interactors.user.list.UserInfo

internal fun User.toUserInfo(): UserInfo {
    return UserInfo(
        id = this.id,
        userName = this.userName,
        type = this.type,
        avatarUrl = this.avatarUrl
    )
}