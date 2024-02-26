package com.ingenious.data.backend

import com.ingenious.data.user.UsersApi

interface BackendApiProvider {

    fun provideUsersApi(): UsersApi
}