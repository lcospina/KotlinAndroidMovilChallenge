package com.enteprise.usecases.repositories

import com.enteprise.domain.City
import com.enteprise.domain.CitySpecificData

interface CityRepository {
    fun SearchCityByName(name: String): List<City>
    fun searchWeatherByCity(idCity: String): CitySpecificData?
}