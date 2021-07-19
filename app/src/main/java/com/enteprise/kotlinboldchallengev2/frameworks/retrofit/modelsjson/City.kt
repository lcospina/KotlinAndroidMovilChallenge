package com.enteprise.kotlinboldchallengev2.frameworks.retrofit.modelsjson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class City(
    @SerializedName("title") @Expose var name: String,
    @SerializedName("location_type") @Expose var type: String,
    @SerializedName("woeid") @Expose var idCity: String
) {

}