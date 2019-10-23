package com.test.githubapp.repository.remote

import com.test.githubapp.model.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getAllGitHubUsers(): Observable<List<UserEntity>>
}