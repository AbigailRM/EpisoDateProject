package com.moviledev.tvseries.dataObjects

import com.google.gson.annotations.SerializedName

data class TvShowEpisodes (
    @SerializedName("season") val season : Int,
    @SerializedName("episode") val episode : Int,
    @SerializedName("name") val name : String,
    @SerializedName("air_date") val air_date : String
)