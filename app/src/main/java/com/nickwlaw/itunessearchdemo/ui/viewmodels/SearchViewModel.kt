package com.nickwlaw.itunessearchdemo.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nickwlaw.itunessearchdemo.domain.ITunesRepository
import com.nickwlaw.itunessearchdemo.domain.ITunesSearchQuery
import com.nickwlaw.itunessearchdemo.domain.ITunesSongSearchQuery
import com.nickwlaw.itunessearchdemo.domain.Song
import com.nickwlaw.itunessearchdemo.foundation.BaseViewModel
import com.nickwlaw.itunessearchdemo.foundation.ErrorUiState
import com.nickwlaw.itunessearchdemo.foundation.UIState
import com.nickwlaw.itunessearchdemo.foundation.bimap
import com.nickwlaw.itunessearchdemo.ui.adapters.SearchResultAdapter
import timber.log.Timber

data class SearchUIState(
    override val errorUiState: ErrorUiState = ErrorUiState(),
    override val isLoading: Boolean = false
) : UIState.ErrorLoadingUIState

class SearchViewModel(
    private val iTunesRepo: ITunesRepository
) : BaseViewModel<UIState.ErrorLoadingUIState>(SearchUIState()) {

    var adapter = SearchResultAdapter(emptyList())

    private val _searchResultsLiveData = MutableLiveData<List<Song>?>()
    val searchResultsLiveData: LiveData<List<Song>?>
        get() = _searchResultsLiveData

    // TODO("handle loading state")
    fun fetchSearchResults(query: ITunesSongSearchQuery) {
        updateState(SearchUIState(isLoading = true))
        _searchResultsLiveData.value = emptyList()
        interact {
            val results = iTunesRepo.searchITunesForSong(query)

            results.bimap({
                val errorUiState = ErrorUiState(true, it.message.orEmpty())
                updateState(SearchUIState(errorUiState, false))
            }, {
                _searchResultsLiveData.value = it
                updateState(SearchUIState(isLoading = false))
            })
        }
    }

    fun updateRecyclerView(songs: List<Song>) {
        adapter.setItems(songs)
    }
}