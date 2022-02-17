package com.praveen.saveoproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.praveen.saveoproject.infra.api.Resource
import com.praveen.saveoproject.model.MovieResponse
import com.praveen.saveoproject.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getMovieResponse() = repository.getPageList()

    fun getResponseFromApi(page: Int): LiveData<Resource<MovieResponse>> {
        return liveData(Dispatchers.IO) {
            val response = repository.getResponseFromAPI(page)
            emit(response)
        }
    }
}