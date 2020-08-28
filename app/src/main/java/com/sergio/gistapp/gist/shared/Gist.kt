package com.sergio.gistapp.gist.shared

data class Gist(val id: String, val owner: Owner, val description: String?, val files: List<File>)