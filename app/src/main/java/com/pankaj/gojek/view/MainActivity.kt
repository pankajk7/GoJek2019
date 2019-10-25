package com.pankaj.gojek.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.pankaj.gojek.R
import com.pankaj.gojek.network.GithubApiService
import com.pankaj.gojek.repo.MainRepository
import com.pankaj.gojek.view_model.MainViewModel

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, MainViewModel.MainViewModelFactory(
            MainRepository().getInstance(), GithubApiService())).get(MainViewModel::class.java)

        viewModel?.fetchGithubRepositories { baseResponse ->
            baseResponse.obj?.let {

            } ?: run {

            }
        }
    }
}
