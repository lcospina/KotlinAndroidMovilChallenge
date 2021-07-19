package com.enteprise.data.repositories

import com.enteprise.data.datasources.CityDataSource
import com.enteprise.domain.City
import com.enteprise.domain.CitySpecificData
import com.enteprise.usecases.repositories.CityRepository

class CityRepositoryImp(
    val dataSource: CityDataSource
) : CityRepository {

    override fun SearchCityByName(name: String): List<City> {
        return dataSource.SearchCityByName(name);
    }

    override fun searchWeatherByCity(idCity: String): CitySpecificData? {
        return dataSource.searchWeatherByCity(idCity)
    }

}