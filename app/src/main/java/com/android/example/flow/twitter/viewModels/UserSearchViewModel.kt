package com.android.example.flow.twitter.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.android.example.flow.twitter.data.models.ResponseWrapper
import com.android.example.flow.twitter.data.models.User
import com.android.example.flow.twitter.data.preferences.PreferencesManager
import com.android.example.flow.twitter.data.repository.SearchUserRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by AjayArya on 2020-08-16
 */
@ExperimentalCoroutinesApi
class UserSearchViewModel
@ViewModelInject
constructor(
    private val preferencesManager: PreferencesManager,
    private val _searchUserRepository: SearchUserRepository
) : ViewModel() {

    /**
     * When this mutable LiveData is observed internally by the LiveData observed in the activity
     */
    private val _searchQueryMutableLiveData = MutableLiveData<String>()

    /**
     * It holds all cached queries
     */
    private val _queriesMutableLiveData = MutableLiveData<MutableSet<String>>()

    /**
     * It observe each text-change of the search View ( except empty )
     */
    private val _textChangeMutableLiveData = MutableLiveData<String>()

    init {
        _queriesMutableLiveData.value = preferencesManager.getAllQueries()
    }

    /**
     * Store queries to local
     * When textChange liveData is altered , it filters out the set containing the textChange
     * and returns a list
     */
    val textChangeLiveData: LiveData<List<String>?> = liveData {
        emitSource(_textChangeMutableLiveData.switchMap { textChange ->
            liveData {
                emit(_queriesMutableLiveData.value?.filter { query ->
                    query.contains(
                        textChange
                    )
                })
            }
        })
    }

    /**
     * LiveData observed in the Activity
     */
    val _twitterLiveData: MutableLiveData<ResponseWrapper<out List<User>?, out Exception?>> =
        MutableLiveData()

    val twitterLiveData: LiveData<ResponseWrapper<out List<User>?, out Exception?>>
        get() = _twitterLiveData

//    val twitteeLiveData: MutableLiveData<ResponseWrapper<out List<User>?, out Exception?>> = liveData {
//
////            emitSource(_searchQueryMutableLiveData.switchMap {
//////                viewModelScope.launch(Main) {
////                _searchUserRepository.getFlowListUser(query).asLiveData()
//////                }
////            })
//
//    }

    /**
     * LiveData object that collects the flow
     * @param query is the twitter alias for which the user is searching
     */
    fun getUsers(query: String) {

        viewModelScope.launch(IO) {
            _searchUserRepository.getFlowListUser(query)
                .onEach {
                    viewModelScope.launch(Main) {
                        _twitterLiveData.value = it
                    }
                }.launchIn(viewModelScope)
        }

        //_searchQueryMutableLiveData.value = query
        preferencesManager.storeQuery(query)
        _queriesMutableLiveData.value = preferencesManager.getAllQueries()

    }

    /**
     * @param query is the text changed query
     */
    fun setTextChange(textChange: String) {
        if (textChange.isNotEmpty())
            _textChangeMutableLiveData.value = textChange
    }
}