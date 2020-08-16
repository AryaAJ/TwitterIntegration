package com.android.example.flow.twitter.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.flow.twitter.data.models.ResponseWrapper
import com.android.example.flow.twitter.data.models.ScreenNameCount
import com.android.example.flow.twitter.data.models.Tweet
import com.android.example.flow.twitter.data.models.User
import com.android.example.flow.twitter.data.repository.TweetsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by AjayArya on 2020-08-16
 */
@ExperimentalCoroutinesApi
class TweetsViewModel @ViewModelInject constructor(private val _tweetsRepository: TweetsRepository) :
    ViewModel() {

    private val _screenNameCountMutableLiveData = MutableLiveData<ScreenNameCount>()

    val _tweetsLiveData: MutableLiveData<ResponseWrapper<out List<Tweet>?, out Exception?>> =
        MutableLiveData()

    val tweetsLiveData: LiveData<ResponseWrapper<out List<Tweet>?, out Exception?>>
        get() = _tweetsLiveData

//    val tweetsLiveData = liveData {
//        emitSource(_screenNameCountMutableLiveData.switchMap { screenNameCount ->
//            _tweetsRepository.getTweets(
//                screenNameCount.screenName,
//                screenNameCount.count
//            ).asLiveData()
//        })
//    }

    fun setScreenNameCount(screenNameCount: ScreenNameCount, id: String) {
        _screenNameCountMutableLiveData.value = screenNameCount

        viewModelScope.launch(Dispatchers.IO) {
            _tweetsRepository.getTweets(screenNameCount.screenName, screenNameCount.count, id)
                .onEach {
                    viewModelScope.launch(Dispatchers.Main) {
                        _tweetsLiveData.value = it
                    }
                }.launchIn(viewModelScope)
        }
    }
}