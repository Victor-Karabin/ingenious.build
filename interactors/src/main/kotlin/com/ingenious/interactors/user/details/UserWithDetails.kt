package com.ingenious.interactors.user.details

import kotlinx.datetime.LocalDateTime

data class UserWithDetails(
    val id: Int,
    val userName: String,
    val nodeId: String,
    val avatarUrl: String,
    val type: String,
    val name: String,
    val company: String?,
    val email: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)