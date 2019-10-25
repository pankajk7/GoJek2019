package com.pankaj.gojek.`interface`

import com.pankaj.gojek.model.BaseResponse

interface GithubApiContract {

    fun getRepositories(callBack : (BaseResponse) -> Unit)
}