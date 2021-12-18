package com.nickwlaw.itunessearchdemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.nickwlaw.itunessearchdemo.databinding.FragmentSearchBinding
import com.nickwlaw.itunessearchdemo.foundation.BaseFragment
import com.nickwlaw.itunessearchdemo.ui.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nickwlaw.itunessearchdemo.BR
import com.nickwlaw.itunessearchdemo.domain.CountryCode
import com.nickwlaw.itunessearchdemo.domain.ITunesSongSearchQuery

class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchView.OnQueryTextListener,
    android.widget.SearchView.OnQueryTextListener {

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var songQuery: ITunesSongSearchQuery

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@SearchFragment.viewModel
            setVariable(BR.viewModel, viewModel)
        }

        viewModel.searchResultsLiveData.observe(viewLifecycleOwner) { results ->
            if (results != null) {
                viewModel.updateRecyclerView(results)
            }
        }

        binding.svSearch.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    // TODO("implement one second delay timer for calls")
    override fun onQueryTextChange(queryText: String?): Boolean {
        queryText ?: return true

        songQuery = ITunesSongSearchQuery(
            queryText,
            CountryCode.UNITED_STATES
        )
        viewModel.fetchSearchResults(songQuery)

        return true
    }
}