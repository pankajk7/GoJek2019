package com.pankaj.gojek.`interface`

import retrofit2.Call

interface GithubApiContract {

    fun getRepositories(callBack : (String) -> Unit): Call<String>
}