package com.lokesh.definelabtask


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("venues/search")
    fun getMatches(
        @Query("ll") location: String = "40.7484,-73.9857",
        @Query("oauth_token") token: String = "NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ",
        @Query("v") version: String = "20180616"
    ): Call<ApiResponse>
}



