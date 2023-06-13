package com.example.datasource.network.networkService.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.datasource.network.WebService
import com.example.datasource.network.mapper.toDomainTvList
import com.example.domain.model.tvList.TvShow
import javax.inject.Inject

class TvShowPagingSource @Inject constructor(
    private val webService: WebService
) : PagingSource<Int, TvShow>() {

    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        return try {
            val prev = params.key ?: 1
            val response = webService.discoverTvShows(page = prev)

            if (response.isSuccessful) {
                val body = response.body()?.toDomainTvList()
                LoadResult.Page(
                    data = body!!,
                    prevKey = if (prev == 1) null else prev - 1,
                    nextKey = if (body.size < 20) null else prev + 1
                )
            } else {
                LoadResult.Error(Exception())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}