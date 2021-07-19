package com.enteprise.usecases.usecases

import com.enteprise.domain.City
import com.enteprise.usecases.repositories.CityRepository


class SearchCityByNameUseCase(val cityRepository: CityRepository) {

    fun invoke(name: String): List<City> {
        return cityRepository.SearchCityByName(name)
    }

}