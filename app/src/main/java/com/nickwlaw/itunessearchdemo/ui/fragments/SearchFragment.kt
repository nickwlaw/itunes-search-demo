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
import com.nickwlaw.itunessearchdemo.domain.ITunesSearchQuery

class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchView.OnQueryTextListener {

    private val viewModel: SearchViewModel by viewModel()

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

            }
        }

        val query = ITunesSearchQuery(
            "That Funny Feeling",
            CountryCode.UNITED_STATES,
            "music",
            "musicTrack",
            "songTerm",
            null,
            null
        )
        viewModel.fetchSearchResults(query)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

}