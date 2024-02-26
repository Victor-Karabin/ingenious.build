package com.ingenious.presentation.user.list

import com.ingenious.interactors.user.list.UserInfo

internal fun UserInfo.toItem(): UserItem {
    return UserItem(
        id = this.id,
        userName = this.userName,
        userType = this.type,
        avatarUrl = this.avatarUrl
    )
}