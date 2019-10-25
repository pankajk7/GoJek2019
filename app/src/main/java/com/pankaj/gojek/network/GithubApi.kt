package com.pankaj.gojek.network

import com.pankaj.gojek.model.GithubRepoResponse
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("repositories")
    fun fetchGithubRepositories() : Call<List<GithubRepoResponse>>
}