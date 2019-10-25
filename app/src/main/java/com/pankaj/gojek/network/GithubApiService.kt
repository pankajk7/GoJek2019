package com.pankaj.gojek.network

import com.pankaj.gojek.`interface`.GithubApiContract
import retrofit2.Call

class GithubApiService: GithubApiContract {

    private val githubApiService by lazy {
        ApiClient.client.create(GithubApi::class.java)
    }

    override fun getRepositories(callBack : (String) -> Unit): Call<String> {
        return githubApiService.fetchGithubRepositories()
    }
}