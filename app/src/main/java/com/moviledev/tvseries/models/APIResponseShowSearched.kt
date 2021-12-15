package com.moviledev.tvseries.models

import com.google.gson.annotations.SerializedName
import com.moviledev.tvseries.dataObjects.TvShowSearched

data class APIResponseShowSearched(
    @SerializedName("tvShow") val tvShowSearched: TvShowSearched
)
