package com.sergio.gistapp.gist.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergio.gistapp.gist.shared.Gist

class GistDetailViewModel: ViewModel() {

    private val _gist = MutableLiveData<Gist>()

    val gist get() = _gist

    fun updateGist(gist: Gist) {
        _gist.value = gist
    }

}