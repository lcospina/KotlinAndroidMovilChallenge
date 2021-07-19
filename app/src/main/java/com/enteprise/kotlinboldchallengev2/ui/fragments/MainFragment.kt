package com.enteprise.kotlinboldchallengev2.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.enteprise.data.repositories.CityRepositoryImp
import com.enteprise.domain.City
import com.enteprise.kotlinboldchallengev2.databinding.FragmentMainBinding
import com.enteprise.kotlinboldchallengev2.frameworks.retrofit.datasourceimplements.CityDataSourceRetroFitImpl
import com.enteprise.kotlinboldchallengev2.ui.viewmodels.MainFragmentModelView
import com.enteprise.movilchallenge.presentation.adapters.CitysResultsAdapter

class MainFragment : Fragment() {

    private lateinit var modelView: MainFragmentModelView
    private lateinit var view: FragmentMainBinding
    private lateinit var adapter: CitysResultsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = FragmentMainBinding.inflate(inflater, container, false)
        initBehavior();
        return view.root
    }

    fun initBehavior() {
        modelView = MainFragmentModelView();
        modelView.getCitys()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer<List<City>> { citys ->
                adapter.setResults(citys)
            })
        view.city.doOnTextChanged { text, _, _, _ ->
            modelView.onTypedText(text.toString())
        }

        //SE DEBE INYECTAR UNA DEPENDENCIA
        adapter = CitysResultsAdapter(
            CityRepositoryImp(
                CityDataSourceRetroFitImpl()
            )
        )

        view.listCitys.setLayoutManager(LinearLayoutManager(context))
        view.listCitys.setAdapter(adapter)
    }

}