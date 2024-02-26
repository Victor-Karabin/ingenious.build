package com.ingenious.presentation.user.details

import androidx.compose.runtime.Stable

@Stable
internal data class Details(
    val id: String,
    val userName: String,
    val nodeId: String,
    val avatarUrl: String,
    val type: String,
    val name: String,
    val company: String,
    val email: String,
    val createdAt: String,
    val updatedAt: String
) {

    internal companion object {
        internal val EMPTY = Details(
            id = "",
            userName = "",
            nodeId = "",
            avatarUrl = "",
            type = "",
            name = "",
            company = "",
            email = "",
            createdAt = "",
            updatedAt = ""
        )
    }
}