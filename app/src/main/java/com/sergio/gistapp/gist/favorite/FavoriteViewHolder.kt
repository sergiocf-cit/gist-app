package com.sergio.gistapp.gist.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sergio.gistapp.databinding.GistCardBinding
import com.sergio.gistapp.gist.list.GistClickListener
import com.sergio.gistapp.gist.model.Gist
import com.squareup.picasso.Picasso

class FavoriteViewHolder(private val binding: GistCardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(gist: Gist, listener: GistClickListener) {
        binding.gist = gist
        binding.listener = listener
        binding.saveButton.isVisible = false

        Picasso.get().load(gist.avatarUrl)
            .into(binding.avatarImage)
    }


    companion object {
        fun from(parent: ViewGroup): FavoriteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = GistCardBinding.inflate(layoutInflater, parent, false)

            return FavoriteViewHolder(binding)
        }
    }
}