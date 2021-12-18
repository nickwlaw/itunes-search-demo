package com.nickwlaw.itunessearchdemo.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    recyclerView.layoutManager = LinearLayoutManager(
        recyclerView.context
    )

    recyclerView.setHasFixedSize(false)
    recyclerView.adapter = adapter
}

@BindingAdapter("loadImageUrl")
fun loadImageUrl(imageView: ImageView, path: String?) {
    path?.let {
        Glide.with(imageView.context).load(path).into(imageView)
    }
}
