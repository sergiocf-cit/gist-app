package com.sergio.gistapp.gist.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sergio.gistapp.databinding.GistCardBinding
import com.sergio.gistapp.gist.shared.Gist
import com.squareup.picasso.Picasso

class GistListViewHolder(private val binding:GistCardBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(gist: Gist) {
        binding.ownerTextView.text = gist.owner.login
        binding.typeTextView.text = gist.files[0].type

        Picasso.get().load(gist.owner.avatar_url)
            .into(binding.avatarImage)

        binding.avatarImage
    }

    companion object {
        fun from(parent: ViewGroup): GistListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = GistCardBinding.inflate(layoutInflater, parent, false)

            return GistListViewHolder(binding)
        }
    }

}
