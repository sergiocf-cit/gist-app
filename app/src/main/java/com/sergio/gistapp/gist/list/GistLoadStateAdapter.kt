package com.sergio.gistapp.gist.list

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class GistLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<GistLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: GistLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): GistLoadStateViewHolder {
        return GistLoadStateViewHolder.create(parent, retry)
    }
}
