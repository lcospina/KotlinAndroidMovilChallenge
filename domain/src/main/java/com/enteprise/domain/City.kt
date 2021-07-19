package com.enteprise.domain

class City(
    var name: String,
    var type: String,
    var idCity: String,
    var expanded: Boolean,
    ) {
    var citySpecificData: CitySpecificData? = null
}