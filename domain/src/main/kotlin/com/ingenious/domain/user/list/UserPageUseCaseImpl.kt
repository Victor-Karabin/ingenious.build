package com.ingenious.domain.user.list

import com.ingenious.boundary.user.list.UsersPageRepo
import com.ingenious.interactors.user.list.UserInfo
import com.ingenious.interactors.user.list.UserPageUseCase
import javax.inject.Inject

class UserPageUseCaseImpl @Inject constructor(
    private val usersPageRepo: UsersPageRepo
) : UserPageUseCase {

    override suspend fun invoke(pageNum: Int, loadSize: Int): Result<List<UserInfo>> {
        return usersPageRepo.loadPage(pageNum, loadSize)
            .map { users -> users.map { user -> user.toUserInfo() } }
    }
}