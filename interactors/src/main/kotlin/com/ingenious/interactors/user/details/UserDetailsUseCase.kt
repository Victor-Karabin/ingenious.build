package com.ingenious.interactors.user.details

interface UserDetailsUseCase {

    suspend operator fun invoke(userName: String): Result<UserWithDetails>
}