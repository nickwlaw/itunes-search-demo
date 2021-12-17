package com.nickwlaw.itunessearchdemo.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nickwlaw.itunessearchdemo.domain.ITunesRepository
import com.nickwlaw.itunessearchdemo.domain.ITunesSearchQuery
import com.nickwlaw.itunessearchdemo.domain.Song
import com.nickwlaw.itunessearchdemo.foundation.BaseViewModel
import com.nickwlaw.itunessearchdemo.foundation.ErrorUiState
import com.nickwlaw.itunessearchdemo.foundation.UIState
import timber.log.Timber

data class SearchUIState(
    override val errorUiState: ErrorUiState = ErrorUiState(),
    override val isLoading: Boolean = false
) : UIState.ErrorLoadingUIState

class SearchViewModel(
    private val iTunesRepo: ITunesRepository
) : BaseViewModel<UIState.ErrorLoadingUIState>(SearchUIState()) {

    private val _searchResultsLiveData = MutableLiveData<List<Song>?>()
    val searchResultsLiveData: LiveData<List<Song>?>
        get() = _searchResultsLiveData

    fun fetchSearchResults(query: ITunesSearchQuery) {
        _searchResultsLiveData.value = emptyList()
        interact {
            val results = iTunesRepo.searchITunesForSong(query)

            when {
                results.isLeft -> {
                    Timber.d("Error fetching results")
                    // TODO("update error handling")
                }
                results.isRight -> {
                    Timber.d(results.toString())
                }
            }

        }
    }
}