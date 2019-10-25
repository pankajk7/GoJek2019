package com.pankaj.gojek.repo

import com.pankaj.gojek.`interface`.GithubApiContract

class MainRepository {

    private var instance: MainRepository? = null

    fun getInstance() = instance ?: MainRepository().also { instance = it }

    fun fetchGithubRepositories(githubApiContract: GithubApiContract, callBack : (String) -> Unit) {
        githubApiContract.getRepositories(callBack)
    }
}