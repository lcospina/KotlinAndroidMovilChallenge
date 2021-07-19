package com.enteprise.kotlinboldchallengev2.frameworks.retrofit.modelsjson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CitySpecificData(
    @SerializedName("consolidated_weather") @Expose var consolidated_weather: List<CityWeather>,
    @SerializedName("timezone") @Expose var timezone: String
) {

}