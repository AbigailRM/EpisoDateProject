package com.moviledev.tvseries.models

import com.google.gson.annotations.SerializedName
import com.moviledev.tvseries.dataObjects.TvShowSearched

data class APIResponseED (
    @SerializedName("total")
    var total : Int,
    @SerializedName("page")
    var page : Int,
    @SerializedName("pages")
    var pages : Int,
    @SerializedName("tv_shows")
    var tvShows : List<TvShow> = arrayListOf()
)