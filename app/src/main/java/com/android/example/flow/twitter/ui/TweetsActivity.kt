package com.android.example.flow.twitter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.flow.twitter.Constants
import com.android.example.flow.twitter.data.models.ScreenNameCount
import com.android.example.flow.twitter.data.models.Tweet
import com.android.example.flow.twitter.ui.adapters.RvTweetsAdapter
import com.android.example.flow.twitter.viewModels.TweetsViewModel
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterConfig
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_tweets.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TweetsActivity : AppCompatActivity() {

    private val _viewModel: TweetsViewModel by viewModels()

    @Inject
    lateinit var config: TwitterConfig

    private lateinit var _rvTweetsAdapter: RvTweetsAdapter
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.android.example.flow.twitter.R.layout.activity_tweets)

        setUpRecyclerView()
        observeTweets()

        intent.getStringExtra(Constants.ID)?.let {
            id = it
        }

        //start the operation
        intent.getStringExtra(Constants.SCREEN_NAME)?.let {
            _viewModel.setScreenNameCount(ScreenNameCount(it, 10), id)
        }

        //OAuth Configuration - for the app - provided module
        Twitter.initialize(config)

    }

    private fun setUpRecyclerView() {
        // users list recyclerView
        _rvTweetsAdapter = RvTweetsAdapter(this)
        rvTweets.layoutManager = LinearLayoutManager(this)
        rvTweets.adapter = _rvTweetsAdapter
    }

    private fun observeTweets() {
        _viewModel.tweetsLiveData.observe(this, Observer {
            it.exception?.let { exception ->
                handleException(exception)
            } ?: kotlin.run {
                handleData(it.data!!)
            }
        })
    }

    private fun handleData(data: List<Tweet>) {
        _rvTweetsAdapter.updateTweetsList(data)
    }

    private fun handleException(exception: Exception) {
        Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()
    }
}
