package com.ingenious.data.user

import com.ingenious.data.user.UserDetailsDto
import com.ingenious.data.user.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// base headers via interceptor. @see HeadersInterceptor
interface UsersApi {

    //https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#list-users
    @GET("/users")
    suspend fun listUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int,
    ): Response<List<UserDto>>

    //https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#get-a-user
    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): Response<UserDetailsDto>
}