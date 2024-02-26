package com.ingenious.domain.user.details

import com.ingenious.boundary.user.details.UserDetailsRepo
import com.ingenious.interactors.user.details.UserDetailsUseCase
import com.ingenious.interactors.user.details.UserWithDetails
import javax.inject.Inject

class UserDetailsUseCaseImpl @Inject constructor(
    private val userDetailsRepo: UserDetailsRepo
) : UserDetailsUseCase {

    override suspend fun invoke(userName: String): Result<UserWithDetails> {
        return userDetailsRepo.details(userName)
            .map { details -> details.toUserWithDetails() }
    }
}