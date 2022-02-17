package com.praveen.saveoproject.infra.api

import com.praveen.saveoproject.model.MovieModel


interface OnCardClicked {

    fun onCardClicked(resultModel: MovieModel)

}