package com.sergio.gistapp.gist.service

import com.sergio.gistapp.gist.shared.File

data class DynamicFileDto(val files:List<FileDto>)

fun DynamicFileDto.toModel(): List<File> {
    val result = mutableListOf<File>()

    for (item in this.files) {
        result.add(File(item.filename, item.type))
    }

    return result
}