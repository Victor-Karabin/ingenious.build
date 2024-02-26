package com.ingenious.di.user.details

import com.ingenious.boundary.user.details.UserDetailsRepo
import com.ingenious.data.user.UsersApi
import com.ingenious.data.user.details.UserDetailsRepoImpl
import com.ingenious.data.user.details.UserDetailsStore
import com.ingenious.domain.user.details.UserDetailsUseCaseImpl
import com.ingenious.interactors.user.details.UserDetailsUseCase
import com.ingeniuos.common.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
class UserDetailsModule {

    @Provides
    @ViewModelScoped
    fun provideUserDetailsUseCase(userDetailsRepo: UserDetailsRepo): UserDetailsUseCase {
        return UserDetailsUseCaseImpl(userDetailsRepo)
    }

    @Provides
    @ViewModelScoped
    fun provideUserDetailsRepo(
        usersApi: UsersApi,
        cache: UserDetailsStore,
        @IoDispatcher
        io: CoroutineDispatcher
    ): UserDetailsRepo {
        return UserDetailsRepoImpl(usersApi, cache, io)
    }
}