package com.moviledev.tvseries.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviledev.tvseries.dataObjects.EpisodateNetwork
import com.moviledev.tvseries.dataObjects.EpisodateServices
import com.moviledev.tvseries.dataObjects.TvShowEpisodes
import com.moviledev.tvseries.dataObjects.TvShowSearched
import com.moviledev.tvseries.models.APIResponseED
import com.moviledev.tvseries.models.APIResponseShowSearched
import com.moviledev.tvseries.models.ResponsePagination
import com.moviledev.tvseries.models.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel:ViewModel(){

    private val _tvShowList = MutableLiveData<List<TvShow>>()
    private val _exception = MutableLiveData<Throwable>()
    private val _selected : MutableLiveData<TvShow> = MutableLiveData()
    private val _pagination : MutableLiveData<ResponsePagination> = MutableLiveData()
    private val _loading : MutableLiveData<Boolean> = MutableLiveData()
    private val _tvShowDetail = MutableLiveData<TvShowSearched>()
    private val _tvShowEpisodes = MutableLiveData<List<TvShowEpisodes>>()

    val tvShowList : LiveData<List<TvShow>> = _tvShowList
    val selected : LiveData<TvShow> = _selected
    val exception : LiveData<Throwable> = _exception
    val pagination : LiveData<ResponsePagination> = _pagination
    val loading : LiveData<Boolean> = _loading
    val tvShowDetail : LiveData<TvShowSearched> = _tvShowDetail
    val tvShowEpisodes : LiveData<List<TvShowEpisodes>> = _tvShowEpisodes

    val service = EpisodateServices.getInstance()

    fun loadTvShows(page: Int = 1) {
        _loading.value = true
        var response = service.getMostPopular(page).enqueue(object : Callback<APIResponseED> {
            override fun onResponse(
                call: Call<APIResponseED>,
                response: Response<APIResponseED>
            ) {
                val responseBody = response.body()
                _tvShowList.value = responseBody!!.tvShows
                _pagination.value = ResponsePagination(
                    responseBody.page,
                    responseBody.total,
                    responseBody.pages
                )
                _loading.value = false
            }

            override fun onFailure(call: Call<APIResponseED>, t: Throwable) {
                _exception.value = t
                _loading.value = false
            }

        })
    }

    fun loadTvShowDetail(showName: String){
        var response = service.getShowDetails(showName).enqueue(object : Callback<APIResponseShowSearched> {
            override fun onResponse(
                call: Call<APIResponseShowSearched>,
                response: Response<APIResponseShowSearched>
            ) {
                val responseBody = response.body()
                _tvShowDetail.value = responseBody!!.tvShowSearched
                _tvShowEpisodes.value = responseBody!!.tvShowSearched.episodes
            }

            override fun onFailure(call: Call<APIResponseShowSearched>, t: Throwable) {
                _exception.value = t
            }

        })
    }

    fun select(tvShow: TvShow) {
        _selected.value = tvShow

    }

    fun search(searchText: String) {
        _loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getShowInfo(searchText).execute()
            val responseBody = response.body()
            _tvShowList.postValue(responseBody!!.tvShows)
            _pagination.postValue(ResponsePagination(
                responseBody.page,
                responseBody.total,
                responseBody.pages
            ))
            _loading.postValue(false)
        }
    }
}