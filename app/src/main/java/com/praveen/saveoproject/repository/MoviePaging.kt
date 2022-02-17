package com.praveen.saveoproject.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.praveen.saveoproject.infra.api.APIClient
import com.praveen.saveoproject.model.MovieModel
import com.praveen.saveoproject.model.MovieResponse
import com.praveen.saveoproject.utils.Constant.API_KEY
import com.praveen.saveoproject.utils.Constant.START_PAGE
import javax.inject.Inject

class MoviePaging @Inject constructor(private val apiClient: APIClient) :
    PagingSource<Int, MovieModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val pageNumber = params.key ?: START_PAGE
        val movieResponse: MovieResponse =
            apiClient.getResponseFromAPI(api_key = API_KEY, pageNumber)
        val resultModel: List<MovieModel> = movieResponse.resultModels

        return try {
            LoadResult.Page(
                data = resultModel,
                prevKey = if (pageNumber == START_PAGE) null else pageNumber - 1,
                nextKey = if (resultModel.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}