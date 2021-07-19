package com.enteprise.kotlinboldchallengev2.frameworks.retrofit.services


import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.modelsjson.City
import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.modelsjson.CitySpecificData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CityServices {

    @GET("/api/location/search/")
    fun searchCitys(
        @Query("query") txtCity: String?
    ): Call<List<City>>


    @GET("/api/location/{query}/")
    fun getWeatherByCity(
        @Path("query") woeId: String?
    ): Call<CitySpecificData>

}