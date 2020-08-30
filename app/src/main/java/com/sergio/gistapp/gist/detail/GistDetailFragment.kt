package com.sergio.gistapp.gist.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sergio.gistapp.R
import com.sergio.gistapp.databinding.FragmentGistDetailBinding
import com.sergio.gistapp.gist.model.Gist
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [GistDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GistDetailFragment : Fragment() {

    private lateinit var binding: FragmentGistDetailBinding

    private val gistDetailViewModel: GistDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gist_detail, container, false)

        val adapter = FileAdapter()
        binding.fileList.adapter =  adapter

        val arguments = GistDetailFragmentArgs.fromBundle(requireArguments())
        gistDetailViewModel.updateGist(arguments.gist)

        gistDetailViewModel.gist.observe(viewLifecycleOwner, Observer {
            updateData(it, adapter)
        })

        return binding.root
    }

    private fun updateData(gist: Gist, adapter: FileAdapter) {
        binding.gist = gist

        if (gist.description == null || gist.description.isEmpty()) {
            binding.descriptionLabel.visibility = View.INVISIBLE
        }

        adapter.data =  gist.files

        Picasso.get().load(gist.avatarUrl)
            .into(binding.avatarImage)
    }

}