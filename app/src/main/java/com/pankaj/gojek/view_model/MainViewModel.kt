package com.pankaj.gojek.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pankaj.gojek.`interface`.GithubApiContract
import com.pankaj.gojek.model.BaseResponse
import com.pankaj.gojek.repo.MainRepository

class MainViewModel(private val repo: MainRepository, private val contract: GithubApiContract)
    : ViewModel() {

    fun fetchGithubRepositories(callback : (BaseResponse) -> Unit) {
        repo.fetchGithubRepositories(contract, callback)
    }

    class MainViewModelFactory(private val repo:  MainRepository, private val contract: GithubApiContract)
        : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repo, contract) as T
        }
    }
}