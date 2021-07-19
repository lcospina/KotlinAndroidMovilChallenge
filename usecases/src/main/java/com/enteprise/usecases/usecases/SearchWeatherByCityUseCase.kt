package com.enteprise.usecases.usecases

import com.enteprise.domain.CitySpecificData
import com.enteprise.usecases.repositories.CityRepository

class SearchWeatherByCityUseCase(val cityRepository: CityRepository) {

    fun searchWeatherByCity(idCity: String): CitySpecificData? {
        return cityRepository.searchWeatherByCity(idCity)
    }
}