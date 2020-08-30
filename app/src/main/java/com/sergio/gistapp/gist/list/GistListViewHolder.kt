package com.sergio.gistapp.gist.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.gistapp.R
import com.sergio.gistapp.databinding.GistCardBinding
import com.sergio.gistapp.gist.model.Gist
import com.squareup.picasso.Picasso

class GistListViewHolder(private val binding:GistCardBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(gist: Gist, listener: GistClickListener, saveListener: GistSaveClickListener) {
        binding.gist = gist
        binding.listener = listener
        binding.saveButton.setOnClickListener {
            gist.favorite = !gist.favorite

            saveListener.onClick(gist)

            setFavoriteIcon(gist.favorite)
        }

        setFavoriteIcon(gist.favorite)

        Picasso.get().load(gist.avatarUrl)
            .into(binding.avatarImage)

    }

    private fun setFavoriteIcon(favorite:Boolean) {
        if(favorite) {
            binding.saveButton.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            binding.saveButton.setImageResource(android.R.drawable.btn_star_big_off)
        }
    }

    companion object {
        fun from(parent: ViewGroup): GistListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = GistCardBinding.inflate(layoutInflater, parent, false)

            return GistListViewHolder(binding)
        }
    }

}
