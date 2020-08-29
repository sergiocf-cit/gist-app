package com.sergio.gistapp.gist.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.gistapp.gist.shared.File


class FileAdapter() : RecyclerView.Adapter<FileViewHolder>() {

    var data = listOf<File>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        return FileViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size


}

