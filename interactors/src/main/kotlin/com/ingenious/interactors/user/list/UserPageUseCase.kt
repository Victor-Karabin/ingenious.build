package com.ingenious.interactors.user.list

import com.ingenious.interactors.user.list.UserInfo

interface UserPageUseCase {

    suspend operator fun invoke(pageNum: Int, loadSize: Int): Result<List<UserInfo>>
}