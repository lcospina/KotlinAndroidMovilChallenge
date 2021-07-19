package com.enteprise.kotlinboldchallengev2.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enteprise.data.repositories.CityRepositoryImp
import com.enteprise.domain.City
import com.enteprise.kotlinboldchallengev2.frameworks.depedencies.Application
import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.datasourceimplements.CityDataSourceRetroFitImpl
import com.enteprise.usecases.repositories.CityRepository
import com.enteprise.usecases.usecases.SearchCityByNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragmentModelView(cityRepository: CityRepository) : ViewModel() {
    private var citys: MutableLiveData<List<City>>;
    private var searchCityByNameUseCase: SearchCityByNameUseCase

    init {
        citys = MutableLiveData();

        searchCityByNameUseCase =
            SearchCityByNameUseCase(cityRepository)

    }

    fun onTypedText(nameCity: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { searchCityByNameUseCase.invoke(nameCity) }
            citys.postValue(result);
        }
    }

    fun getCitys(): LiveData<List<City>> {
        return citys;
    }
}