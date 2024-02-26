package com.ingenious.di.user.list

import com.ingenious.boundary.user.list.UsersPageRepo
import com.ingenious.data.user.UsersApi
import com.ingenious.data.user.list.UsersPageRepoImpl
import com.ingenious.data.user.list.UsersPageStore
import com.ingenious.domain.user.list.UserPageUseCaseImpl
import com.ingenious.interactors.user.list.UserPageUseCase
import com.ingeniuos.common.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
class UserListModule {

    @Provides
    @ViewModelScoped
    fun provideUsersPageUseCase(usersPageRepo: UsersPageRepo): UserPageUseCase {
        return UserPageUseCaseImpl(usersPageRepo)
    }

    @Provides
    @ViewModelScoped
    fun provideUsersPageRepo(
        usersApi: UsersApi,
        cache: UsersPageStore,
        @IoDispatcher
        io: CoroutineDispatcher
    ): UsersPageRepo {
        return UsersPageRepoImpl(usersApi, cache, io)
    }
}