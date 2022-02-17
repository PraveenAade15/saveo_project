package com.praveen.saveoproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.praveen.saveoproject.R
import com.praveen.saveoproject.databinding.FragmentDetailsBinding
import com.praveen.saveoproject.model.MovieModel
import com.praveen.saveoproject.utils.Constant.MOVIE_URL


class DetailsFragment : Fragment() {


    private lateinit var binding: FragmentDetailsBinding
    lateinit var movieModel: MovieModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieModel = arguments?.getSerializable("resultModel") as MovieModel

        binding.apply {
            movieModel.apply {
                Glide.with(ivMovieThumbnail).load(MOVIE_URL + posterPath)
                    .into(ivMovieThumbnail)
                tvMovieName.text = originalTitle
                tvTime.text = releaseDate
                tvSynopsisData.text = overview
                starRatingBar.rating = voteAverage.toString().toFloat() / 2
                tvRatings.text = "Rating: ${voteAverage.toString()}"
                tvReview.text = "Popularity: ${popularity.toString()}"
                tvGenre1.text = "comedy"
                tvGenre2.text = "adventure"
                tvGenre3.text = "romance"
                vBack.setOnClickListener {
                    Navigation.findNavController(it).navigate(R.id.action_detailsFragment_to_landingFragment)
                }
            }
        }

    }


}