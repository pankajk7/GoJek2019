package com.pankaj.gojek.network

import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("repositories")
    fun fetchGithubRepositories() : Call<String>
}