package com.sergio.gistapp.gist.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sergio.gistapp.R
import com.sergio.gistapp.core.BASE_URL
import com.sergio.gistapp.databinding.FragmentGistListBinding
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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

        gistListViewModel.gist.observe(viewLifecycleOwner, Observer {
            Log.i("GistListFragment", it!!.toString())
        })

        return binding.root
    }

}