package com.praveen.saveoproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.praveen.saveoproject.R
import com.praveen.saveoproject.databinding.FragmentLandingBinding
import com.praveen.saveoproject.infra.api.OnCardClicked
import com.praveen.saveoproject.infra.api.Status
import com.praveen.saveoproject.model.MovieModel
import com.praveen.saveoproject.ui.adapters.MovieAdapter
import com.praveen.saveoproject.ui.adapters.NewMovieAdapter
import com.praveen.saveoproject.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LandingFragment : Fragment(), OnCardClicked {

    val viewModel: MovieViewModel by viewModels()
    private lateinit var homeBinding: FragmentLandingBinding
    lateinit var movieAdapter: MovieAdapter
    private var emptyList = emptyList<MovieModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewModel.getMovieResponse().observe(viewLifecycleOwner, Observer {
            it?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    movieAdapter.submitData(it)
                }
            }
        })

        viewModel.getResponseFromApi(1).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.ERROR -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

                Status.SUCCESS -> {
                    emptyList = it.data?.resultModels as ArrayList<MovieModel>
                    val adaptor = NewMovieAdapter(emptyList, this)
                    homeBinding.rvTopMovies.adapter = adaptor
                }
            }
        })
    }

    private fun setAdapter() {
        movieAdapter = MovieAdapter(this)
        homeBinding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
    }


    override fun onCardClicked(resultModel: MovieModel) {
        val bundle = Bundle()
        bundle.putSerializable("resultModel",resultModel)
        Navigation.findNavController(requireView()).navigate(R.id.action_landingFragment_to_detailsFragment,bundle)
    }
}