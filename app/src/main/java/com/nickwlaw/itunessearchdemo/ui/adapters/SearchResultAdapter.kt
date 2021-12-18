package com.nickwlaw.itunessearchdemo.ui.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nickwlaw.itunessearchdemo.R
import com.nickwlaw.itunessearchdemo.BR
import com.nickwlaw.itunessearchdemo.domain.Song
import com.nickwlaw.itunessearchdemo.ui.fragments.SearchDetailFragment
import com.nickwlaw.itunessearchdemo.ui.viewmodels.SearchViewModel
import org.koin.androidx.compose.getViewModel

class SearchResultAdapter(
    searchResults: List<Song>,
    private val onClick: (song: Song) -> Unit
) :
    RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    lateinit var context: Context
    private var _searchResults = searchResults

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            viewType,
            parent,
            false
        )

        return ViewHolder(binding).listen { position, type ->
            val item = _searchResults[position]
            onClick(item)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_searchResults[position])
    }

    override fun getItemCount(): Int = _searchResults.size

    override fun getItemViewType(position: Int): Int = R.layout.item_search_result

    fun setItems(searchResults: List<Song>) {
        this._searchResults = searchResults
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Song?) {
            binding.apply {
                setVariable(BR.result, item)
                executePendingBindings()
            }
        }
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(bindingAdapterPosition, itemViewType)
            it.findNavController().navigate(R.id.action_SearchFragment_to_SearchDetailFragment)
        }
        return this
    }
}