package com.praveen.saveoproject.model

import com.google.gson.annotations.SerializedName
import com.praveen.saveoproject.model.MovieModel

data class MovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val resultModels: List<MovieModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int

)