package com.pankaj.gojek.repo

import com.pankaj.gojek.`interface`.GithubApiContract
import com.pankaj.gojek.model.BaseResponse
import com.pankaj.gojek.model.GithubRepoResponse

class MainRepository {

    private var instance: MainRepository? = null

    fun getInstance() = instance ?: MainRepository().also { instance = it }

    fun fetchGithubRepositories(githubApiContract: GithubApiContract, callBack : (BaseResponse) -> Unit) {
        githubApiContract.getRepositories(callBack)
    }
}