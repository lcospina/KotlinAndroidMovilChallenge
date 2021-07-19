package com.enteprise.kotlinboldchallengev2.frameworks.depedencies

import android.app.Application
import com.enteprise.data.repositories.CityRepositoryImp
import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.datasourceimplements.CityDataSourceRetroFitImpl
import com.enteprise.usecases.repositories.CityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModuleDI {

    @Provides
    @Singleton
    fun repositoryProvider(): CityRepositoryImp = CityRepositoryImp(CityDataSourceRetroFitImpl())
}