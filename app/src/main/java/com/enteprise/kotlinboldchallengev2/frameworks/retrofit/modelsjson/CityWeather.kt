package com.enteprise.kotlinboldchallengev2.frameworks.retrofit.modelsjson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CityWeather(
    @SerializedName("weather_state_name") @Expose var weather_state_name: String,
    @SerializedName("weather_state_abbr") @Expose var weather_state_abbr: String,
    @SerializedName("applicable_date") @Expose var applicable_date: String,
    @SerializedName("min_temp") @Expose var min_temp: String,
    @SerializedName("max_temp") @Expose var max_temp: String,
    @SerializedName("wind_speed") @Expose var wind_speed: String
) {
}
