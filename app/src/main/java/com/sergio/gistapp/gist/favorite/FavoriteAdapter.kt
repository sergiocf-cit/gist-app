package com.sergio.gistapp.gist.favorite

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.gistapp.gist.list.GistClickListener
import com.sergio.gistapp.gist.model.Gist

class FavoriteAdapter(private val listener: GistClickListener) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    var data = listOf<Gist>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    override fun getItemCount() = data.size
}