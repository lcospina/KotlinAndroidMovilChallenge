package com.enteprise.kotlinboldchallengev2.frameworks.depedencies

import android.app.Application
import com.enteprise.data.repositories.CityRepositoryImp
import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.datasourceimplements.CityDataSourceRetroFitImpl

class Application : Application() {
     val cityRepository: CityRepositoryImp = CityRepositoryImp(
        CityDataSourceRetroFitImpl()
    )
}