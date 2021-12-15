package com.moviledev.tvseries.dataObjects

import com.moviledev.tvseries.models.APIResponseED
import com.moviledev.tvseries.models.APIResponseShowSearched
import com.moviledev.tvseries.models.TvShow
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodateServices {

    @GET("/api/most-popular")
    fun getMostPopular(
        @Query("page") page:Int = 1
    ) : Call<APIResponseED>

    @GET("/api/search")
    fun getShowInfo(
        @Query("q") q: String
    ):Call<APIResponseED>

    @GET("/api/show-details")
    fun getShowDetails(
        @Query("q") detail: String = ""
    ):Call<APIResponseShowSearched>

    companion object{
        private var _instance:EpisodateServices? = null
        fun getInstance():EpisodateServices{

            if (_instance==null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.episodate.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .build()

                _instance = retrofit.create(EpisodateServices::class.java)
            }
            return _instance!!
        }
    }
}
