package com.ingenious.presentation.user.details

import com.ingenious.interactors.user.details.UserWithDetails
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.text.SimpleDateFormat
import java.util.Locale

private const val DETAILS_DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss"
private val DetailsFormatter by lazy {
    SimpleDateFormat(DETAILS_DATETIME_FORMAT, Locale.getDefault())
}

internal fun UserWithDetails.toDetails(): Details {
    val createdAtTimestamp = this.createdAt.toInstant(TimeZone.currentSystemDefault())
        .toEpochMilliseconds()

    val updatedAtTimestamp = this.updatedAt.toInstant(TimeZone.currentSystemDefault())
        .toEpochMilliseconds()

    return Details(
        id = this.id.toString(),
        userName = this.userName,
        nodeId = this.nodeId,
        avatarUrl = this.avatarUrl,
        type = this.type,
        name = this.name,
        company = this.company ?: "",
        email = this.email ?: "",
        createdAt = DetailsFormatter.format(createdAtTimestamp),
        updatedAt = DetailsFormatter.format(updatedAtTimestamp)
    )
}