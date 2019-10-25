package com.pankaj.gojek.network

import com.pankaj.gojek.`interface`.GithubApiContract
import com.pankaj.gojek.model.BaseResponse
import com.pankaj.gojek.model.FailureResponse
import com.pankaj.gojek.model.GithubRepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubApiService : GithubApiContract {

    private val githubApiService by lazy {
        ApiClient.client.create(GithubApi::class.java)
    }

    override fun getRepositories(callBack: (BaseResponse) -> Unit) {
        githubApiService.fetchGithubRepositories()
            .enqueue(object : Callback<List<GithubRepoResponse>> {
                override fun onFailure(call: Call<List<GithubRepoResponse>>, t: Throwable) {
                    val obj = BaseResponse()
                    obj.failureResponse = FailureResponse(t.message)
                    callBack(obj)
                }

                override fun onResponse(call: Call<List<GithubRepoResponse>>,
                    response: Response<List<GithubRepoResponse>>) {
                    val obj = BaseResponse()
                    if (response.isSuccessful)
                        obj.obj = response.body()
                    else
                        obj.failureResponse = FailureResponse(response.message(), response.code())
                    callBack(obj)
                }
            })
    }
}