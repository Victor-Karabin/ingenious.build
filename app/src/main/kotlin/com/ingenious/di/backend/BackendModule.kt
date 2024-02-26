package com.ingenious.di.backend

import app.BuildConfig
import com.ingenious.data.backend.BackendApiProvider
import com.ingenious.data.backend.BackendApiProviderImpl
import com.ingenious.data.user.UsersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BackendModule {

    @Provides
    @Singleton
    fun provideBackendApiProvider(): BackendApiProvider {
        return BackendApiProviderImpl(BuildConfig.BACKEND_BASE_URL, BuildConfig.BACKEND_API_VERSION)
    }

    @Provides
    @Singleton
    fun provideFeedsApi(apiProvider: BackendApiProvider): UsersApi {
        return apiProvider.provideUsersApi()
    }
}