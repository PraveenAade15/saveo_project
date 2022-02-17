package com.praveen.saveoproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.praveen.saveoproject.R
import com.praveen.saveoproject.databinding.ItemMoviePagingBinding
import com.praveen.saveoproject.infra.api.OnCardClicked
import com.praveen.saveoproject.model.MovieModel
import com.praveen.saveoproject.utils.Constant.MOVIE_URL
import java.util.*

class NewMovieAdapter(
    val resultModelList: List<MovieModel>,
    val onCardClicked: OnCardClicked
) : RecyclerView.Adapter<NewMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMoviesViewHolder {

        val binding: ItemMoviePagingBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_paging, parent, false
            )
        return NewMoviesViewHolder(binding, onCardClicked)
    }

    override fun onBindViewHolder(holder: NewMoviesViewHolder, position: Int) {
        val resultModel = resultModelList[position]
        holder.setData(resultModel)
    }

    override fun getItemCount(): Int {
        return resultModelList.size
    }


}

class NewMoviesViewHolder(
    val itemLayoutBinding: ItemMoviePagingBinding,
    val onCardClicked: OnCardClicked
) :
    RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun setData(resultModel: MovieModel) {
        itemLayoutBinding.apply {

            Glide.with(ivMovieCard).load(MOVIE_URL + resultModel.posterPath).into(ivMovieCard)

            ivMovieCard.setOnClickListener {
                onCardClicked.onCardClicked(resultModel)
            }
        }
    }
}


