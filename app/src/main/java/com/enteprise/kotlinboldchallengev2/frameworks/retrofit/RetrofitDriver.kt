package com.enteprise.kotlinboldchallengev2.frameworks.retrofit

import android.provider.Telephony.TextBasedSmsColumns.BODY
import com.enteprise.kotlinboldchallengev2.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level

class RetrofitDriver {

    private var retrofit: Retrofit;

    init {

        val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.CITY_SEARCH_SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    fun getRetrofit(): Retrofit {
        return retrofit;
    }
}