package com.sergio.gistapp.gist.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sergio.gistapp.R
import com.sergio.gistapp.databinding.FragmentGistListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [GistListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GistListFragment : Fragment() {
    private val gistListViewModel: GistListViewModel by viewModel()

    private lateinit var binding: FragmentGistListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gist_list, container, false)

        val adapter = GistListAdapter()
        binding.gistList.adapter = adapter

        gistListViewModel.gist.observe(viewLifecycleOwner, Observer {
            adapter.data = it
        })

        return binding.root
    }
}