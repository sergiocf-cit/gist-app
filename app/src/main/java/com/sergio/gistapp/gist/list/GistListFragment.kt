package com.sergio.gistapp.gist.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.sergio.gistapp.R
import com.sergio.gistapp.databinding.FragmentGistListBinding
import com.sergio.gistapp.gist.model.Gist
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
    private val adapter = GistListAdapter(GistClickListener { navigateToGistDetail(it) },
        GistSaveClickListener{favoriteGist(it)})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gist_list, container, false)

        binding.retryButton.setOnClickListener { adapter.retry() }


        initAdapter()

        search("")

        return binding.root
    }

    private fun initAdapter() {
        binding.gistList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = GistLoadStateAdapter { adapter.retry() },
            footer = GistLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->

            binding.gistList.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.INVISIBLE
            binding.retryButton.visibility = View.INVISIBLE

            if(loadState.source.refresh is LoadState.NotLoading) {
                binding.gistList.visibility = View.VISIBLE
            }

            if(loadState.source.refresh is LoadState.Loading) {
                binding.progressBar.visibility = View.VISIBLE
            }

            if(loadState.source.refresh is LoadState.Error) {
                binding.retryButton.visibility = View.VISIBLE
            }

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    requireContext(),
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
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

    private fun favoriteGist(gist: Gist) {
        gistListViewModel.favoriteGist(gist)

        val msg = if(gist.favorite) R.string.add_favorite else R.string.remove_favorite
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}

