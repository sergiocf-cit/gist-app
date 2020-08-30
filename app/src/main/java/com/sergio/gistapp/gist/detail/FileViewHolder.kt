package com.sergio.gistapp.gist.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.gistapp.databinding.FileItemBinding
import com.sergio.gistapp.gist.model.File

class FileViewHolder(private val binding: FileItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(file: File) {
        binding.file = file
    }

    companion object {
        fun from(parent: ViewGroup): FileViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FileItemBinding.inflate(layoutInflater, parent, false)

            return FileViewHolder(binding)
        }
    }
}