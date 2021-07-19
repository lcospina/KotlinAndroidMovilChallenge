package com.enteprise.kotlinboldchallengev2.frameworks.retrofit.datasourceimplements

import com.enteprise.data.datasources.remote.CityDataSourceRemote
import com.enteprise.domain.City

import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.RetrofitDriver
import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.services.CityServices
import java.io.IOException
import kotlin.collections.ArrayList
import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.mapers.cityJSONtocityDomain
import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.mapers.citySpecificDataJSONtocitySpecificDataDomain
import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.modelsjson.CitySpecificData

class CityDataSourceRetroFitImpl : CityDataSourceRemote {

    private var retrofit: RetrofitDriver;
    private lateinit var cityServices: CityServices;

    init {
        retrofit = RetrofitDriver();
    }

    override fun SearchCityByName(name: String): List<City> {
        cityServices = retrofit.getRetrofit().create(CityServices::class.java);
        var data: List<com.enteprise.kotlinboldchallengev2.frameworks.retrofit.modelsjson.City>? =
            null
        try {
            data = cityServices.searchCitys(name).execute().body()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (data == null) {
            data = ArrayList()
        }
        return cityJSONtocityDomain(data)
    }

    override fun searchWeatherByCity(idCity: String): com.enteprise.domain.CitySpecificData? {
        cityServices = retrofit.getRetrofit().create(CityServices::class.java);
        var data: CitySpecificData? = null
        try {
            data = cityServices.getWeatherByCity(idCity).execute().body()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return citySpecificDataJSONtocitySpecificDataDomain(data)
    }

}