package com.enteprise.kotlinboldchallengev2.frameworks.retrofit.mapers

import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.modelsjson.City
import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.modelsjson.CitySpecificData

fun cityJSONtocityDomain(citys: List<City>): List<com.enteprise.domain.City> {

    val citysDomain: ArrayList<com.enteprise.domain.City> = ArrayList()
    for (city in citys) {
        var cityTempDomain: com.enteprise.domain.City =
            com.enteprise.domain.City(
                name = city.name,
                type = city.type,
                idCity = city.idCity,
                expanded = false
            );
        citysDomain.add(cityTempDomain)
    }

    return citysDomain
}

fun citySpecificDataJSONtocitySpecificDataDomain(city: CitySpecificData?): com.enteprise.domain.CitySpecificData? {
    if (city != null) {

        val weathers: ArrayList<com.enteprise.domain.CityWeather> = ArrayList()
        for (weather in city.consolidated_weather) {
            var weatherTempDomain: com.enteprise.domain.CityWeather =
                com.enteprise.domain.CityWeather(
                    weather_state_name = weather.weather_state_name,
                    weather_state_abbr = weather.weather_state_abbr,
                    applicable_date = weather.applicable_date,
                    min_temp = weather.min_temp,
                    max_temp = weather.max_temp,
                    wind_speed = weather.wind_speed,
                );
            weathers.add(weatherTempDomain)
        }

        var cityTempDomain: com.enteprise.domain.CitySpecificData =
            com.enteprise.domain.CitySpecificData(
                timezone = city.timezone,
                consolidated_weather = weathers
            )
        return cityTempDomain
    } else {
        return null
    }
}
