package com.ingenious.di.user

import com.ingenious.data.user.details.UserDetailsStore
import com.ingenious.data.user.details.UserDetailsStoreImpl
import com.ingenious.data.user.list.UsersPageStore
import com.ingenious.data.user.list.UsersPageStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserCachesModule {

    @Binds
    @Singleton
    fun bindUserDetailsCache(cache: UserDetailsStoreImpl): UserDetailsStore

    @Binds
    @Singleton
    fun bindUserPageCache(cache: UsersPageStoreImpl): UsersPageStore
}