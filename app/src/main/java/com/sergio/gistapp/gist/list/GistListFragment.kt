package com.sergio.gistapp.gist.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sergio.gistapp.R
import com.sergio.gistapp.databinding.FragmentGistListBinding
import com.sergio.gistapp.gist.shared.Gist
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [GistListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GistListFragment : Fragment() {
    private val gistListViewModel: GistListViewModel by viewModel()
    private lateinit var binding: FragmentGistListBinding
    private var searchJob: Job? = null
    private val adapter = GistListAdapter(GistClickListener { navigateToGistDetail(it) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gist_list, container, false)

        initAdapter()

        search("")

        return binding.root
    }

    private fun initAdapter() {
        binding.gistList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = GistLoadStateAdapter { adapter.retry() },
            footer = GistLoadStateAdapter { adapter.retry() }
        )
    }

    private fun search(userName: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            gistListViewModel.getAll(userName).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun navigateToGistDetail(gist: Gist) {
        this.findNavController()
            .navigate(GistListFragmentDirections.actionGistListFragmentToGistDetailFragment(gist))

    }
}

