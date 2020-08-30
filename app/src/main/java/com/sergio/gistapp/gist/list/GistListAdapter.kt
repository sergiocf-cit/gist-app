package com.sergio.gistapp.gist.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sergio.gistapp.gist.model.Gist

class GistListAdapter(private val listener: GistClickListener,
                      private val saveListener: GistSaveClickListener ):
    PagingDataAdapter<Gist, GistListViewHolder>(GIST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistListViewHolder {
        return GistListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GistListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, listener, saveListener)
        }
    }

    companion object {
        private val GIST_COMPARATOR = object : DiffUtil.ItemCallback<Gist>() {
            override fun areItemsTheSame(oldItem: Gist, newItem: Gist): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Gist, newItem: Gist): Boolean =
                oldItem == newItem
        }
    }
}

class GistClickListener(val clickListener: (gist: Gist) -> Unit) {
    fun onClick(gist: Gist) = clickListener(gist)
}

class GistSaveClickListener(val clickSaveListener: (gist: Gist) -> Unit) {
    fun onClick(gist: Gist) = clickSaveListener(gist)
}