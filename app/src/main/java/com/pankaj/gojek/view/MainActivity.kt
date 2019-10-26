package com.pankaj.gojek.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pankaj.gojek.R
import com.pankaj.gojek.model.GithubRepoResponse
import com.pankaj.gojek.network.GithubApiService
import com.pankaj.gojek.repo.MainRepository
import com.pankaj.gojek.util.AppUtil
import com.pankaj.gojek.util.gone
import com.pankaj.gojek.util.visible
import com.pankaj.gojek.view_model.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_retry_layout.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, MainViewModel.MainViewModelFactory(
            MainRepository().getInstance(), GithubApiService())).get(MainViewModel::class.java)

        fetchRepositories()
        tv_retry?.setOnClickListener { fetchRepositories() }
    }

    override fun onRefresh() {
        swipeRefreshLayout?.isRefreshing = true
        fetchRepositories()
    }

    private fun fetchRepositories() {
        if (!AppUtil.isNetworkAvailable(this@MainActivity)) {
            showRetry()
            return
        }
        include_view_retry?.gone()
        viewModel?.fetchGithubRepositories { baseResponse ->
            swipeRefreshLayout?.isRefreshing = false
            baseResponse.obj?.let {
                rv_repo.layoutManager = LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.VERTICAL, false)
                rv_repo.adapter = GithubRepoRVAdapter(this@MainActivity,
                    it as MutableList<GithubRepoResponse>)
            } ?: run {
                showRetry()
            }
        }
    }

    private fun showRetry() {
        include_view_retry?.visible()
    }
}
