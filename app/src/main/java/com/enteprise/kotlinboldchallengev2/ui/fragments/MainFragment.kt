package com.enteprise.kotlinboldchallengev2.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.enteprise.data.repositories.CityRepositoryImp
import com.enteprise.domain.City
import com.enteprise.kotlinboldchallengev2.databinding.FragmentMainBinding
import com.enteprise.kotlinboldchallengev2.frameworks.depedencies.Application
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

        //Inyeccion de dependencia manual
        modelView =
            MainFragmentModelView((requireActivity().application as Application).cityRepository);
        modelView.getCitys()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer<List<City>> { citys ->
                adapter.setResults(citys)
            })


        /*
        view.city.doOnTextChanged { text, _, _, _ ->
            //modelView.onTypedText(text.toString())
        }
        */

        view.city.addTextChangedListener(object : TextWatcher {
            private val mHandler = Handler()
            var data: Editable? = null
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                afterTextChangedDoPost(editable)
            }

            fun afterTextChangedDoPost(editable: Editable?) {
                mHandler.removeCallbacks(mFilterTask)
                mHandler.postDelayed(mFilterTask, 100)
                data = editable
            }

            var mFilterTask = Runnable {
                if (data != null) {
                    println("--->" + data.toString())
                    modelView.onTypedText(data.toString())
                }
            }
        })


        //Inyeccion de dependencia manual
        adapter = CitysResultsAdapter(
            (requireActivity().application as Application).cityRepository
        )

        view.listCitys.setLayoutManager(LinearLayoutManager(context))
        view.listCitys.setAdapter(adapter)
    }

}