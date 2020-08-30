package com.sergio.gistapp.gist.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sergio.gistapp.R
import com.sergio.gistapp.databinding.FragmentFavoriteBinding
import com.sergio.gistapp.databinding.FragmentGistDetailBinding
import com.sergio.gistapp.gist.list.GistClickListener
import com.sergio.gistapp.gist.list.GistListFragmentDirections
import com.sergio.gistapp.gist.list.GistListViewModel
import com.sergio.gistapp.gist.model.Gist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)

        val adapter = FavoriteAdapter(GistClickListener { navigateToGistDetail(it) })
        binding.gistFavoriteList.adapter = adapter

        favoriteViewModel.favorites.observe(viewLifecycleOwner, Observer {
            adapter.data = it
        })

        return binding.root
    }


    private fun navigateToGistDetail(gist: Gist) {
        this.findNavController()
            .navigate(FavoriteFragmentDirections.actionFavoriteFragmentToGistDetailFragment(gist))

    }


}