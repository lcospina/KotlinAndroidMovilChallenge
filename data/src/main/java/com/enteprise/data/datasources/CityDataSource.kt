package com.enteprise.data.datasources

import com.enteprise.data.datasources.local.CityDataSourceLocal
import com.enteprise.data.datasources.remote.CityDataSourceRemote
import com.enteprise.domain.City
import com.enteprise.domain.CitySpecificData

interface CityDataSource {
    fun SearchCityByName(name: String): List<City>
    fun searchWeatherByCity(idCity: String): CitySpecificData?
}