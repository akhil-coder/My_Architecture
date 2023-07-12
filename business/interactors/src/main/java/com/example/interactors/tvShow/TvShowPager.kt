package com.example.interactors.tvShow

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.datasource.network.WebService
import com.example.datasource.network.networkService.pagingSource.TvShowPagingSource
import com.example.domain.model.tvList.TvShow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowPager @Inject constructor(private val service: WebService) {

    fun getDiscoverMovieStream(): Flow<PagingData<TvShow>> {
        return Pager(config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = { TvShowPagingSource(service) }).flow
    }
}