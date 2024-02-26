package com.ingenious.presentation.user.list

import androidx.compose.runtime.Stable

@Stable
internal data class UserItem(
    val id: Int,
    val userName: String,
    val avatarUrl: String,
    val userType: String
)
