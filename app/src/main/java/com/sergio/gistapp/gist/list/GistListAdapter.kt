package com.sergio.gistapp.gist.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.gistapp.gist.shared.Gist

class GistListAdapter: RecyclerView.Adapter<GistListViewHolder>() {

    var data = listOf<Gist>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistListViewHolder {
        return GistListViewHolder.from(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: GistListViewHolder, position: Int) {
        holder.bind(data[position])
    }
}