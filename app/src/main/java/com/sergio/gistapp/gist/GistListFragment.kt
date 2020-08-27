package com.sergio.gistapp.gist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sergio.gistapp.R


/**
 * A simple [Fragment] subclass.
 * Use the [GistListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GistListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gist_list, container, false)
    }


}