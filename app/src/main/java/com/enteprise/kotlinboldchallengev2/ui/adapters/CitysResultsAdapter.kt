package com.enteprise.movilchallenge.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.enteprise.data.repositories.CityRepositoryImp
import com.enteprise.kotlinboldchallengev2.utils.stringCut
import com.enteprise.domain.City
import com.enteprise.kotlinboldchallengev2.databinding.CityItemBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import java.util.*

class CitysResultsAdapter(var cityRepositoryImp: CityRepositoryImp) :
    RecyclerView.Adapter<CitysResultsAdapter.CitysResultsHolder>() {
    private var results: List<City> = ArrayList<City>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitysResultsHolder {
        val binding: CityItemBinding =
            CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CitysResultsHolder(binding)
    }


    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: CitysResultsHolder, position: Int) {
        val city: City = results[position]
        holder.viewBinding.cityTitle.setText(city.name)
        holder.viewBinding.cityType.setText(city.type)

        holder.viewBinding.cardItem.setOnClickListener(View.OnClickListener {
            city.expanded = !city.expanded
            notifyItemChanged(position)
        })


        if (city.expanded) {
            if (city.citySpecificData == null) {
                holder.viewBinding.timesLayout.setVisibility(View.GONE)
                holder.viewBinding.progressBar.setVisibility(View.VISIBLE)
                getDataCity(city, holder.viewBinding.cardItem.getContext(), holder, true)
            } else {
                holder.viewBinding.timesLayout.setVisibility(View.VISIBLE)
                holder.viewBinding.progressBar.setVisibility(View.GONE)
                getDataCity(city, holder.viewBinding.cardItem.getContext(), holder, false)
            }
        } else {
            holder.viewBinding.timesLayout.setVisibility(View.GONE)
            holder.viewBinding.progressBar.setVisibility(View.GONE)
        }
    }

    private fun getDataCity(
        cityShow: City,
        context: Context,
        holder: CitysResultsHolder,
        withInternet: Boolean
    ) {

        val informationCity: MutableLiveData<City> = MutableLiveData<City>()
        informationCity.observe(
            (context as LifecycleOwner),
            { city ->
                if (city == null || city.citySpecificData == null || city.citySpecificData!!
                        .consolidated_weather.size < 4
                ) {
                    holder.viewBinding.timesLayout.setVisibility(View.GONE)
                    holder.viewBinding.progressBar.setVisibility(View.GONE)
                } else {
                    Glide.with(holder.viewBinding.weatherImgDay1)
                        .load(
                            "https://www.metaweather.com/static/img/weather/png/" + city.citySpecificData!!
                                .consolidated_weather.get(0).weather_state_abbr
                                .toString() + ".png"
                        )
                        .into(holder.viewBinding.weatherImgDay1)
                    holder.viewBinding.weatherDate1.setText(
                        city.citySpecificData!!.consolidated_weather.get(0)
                            .applicable_date
                    )
                    holder.viewBinding.weatherState1.setText(
                        city.citySpecificData!!.consolidated_weather.get(0)
                            .weather_state_name
                    )
                    holder.viewBinding.weatherTemp1.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(0)
                                .min_temp
                        ).toString() + "°C"
                    )
                    holder.viewBinding.weatherTempMax1.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(0)
                                .max_temp
                        ).toString() + "°C"
                    )
                    holder.viewBinding.weatherWint1.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(0)
                                .wind_speed
                        ).toString() + "mph"
                    )
                    Glide.with(holder.viewBinding.weatherImgDay2)
                        .load(
                            "https://www.metaweather.com/static/img/weather/png/" + city.citySpecificData!!
                                .consolidated_weather.get(1).weather_state_abbr
                                .toString() + ".png"
                        )
                        .into(holder.viewBinding.weatherImgDay2)
                    holder.viewBinding.weatherDate2.setText(
                        city.citySpecificData!!.consolidated_weather.get(1)
                            .applicable_date
                    )
                    holder.viewBinding.weatherState2.setText(
                        city.citySpecificData!!.consolidated_weather.get(1)
                            .weather_state_name
                    )
                    holder.viewBinding.weatherTemp2.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(1)
                                .min_temp
                        ).toString() + "°C"
                    )
                    holder.viewBinding.weatherTempMax2.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(1)
                                .max_temp
                        ).toString() + "°C"
                    )
                    holder.viewBinding.weatherWint2.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(1)
                                .wind_speed
                        ).toString() + "mph"
                    )
                    Glide.with(holder.viewBinding.weatherImgDay3)
                        .load(
                            "https://www.metaweather.com/static/img/weather/png/" + city.citySpecificData!!
                                .consolidated_weather.get(2).weather_state_abbr
                                .toString() + ".png"
                        )
                        .into(holder.viewBinding.weatherImgDay3)
                    holder.viewBinding.weatherDate3.setText(
                        city.citySpecificData!!.consolidated_weather.get(2)
                            .applicable_date
                    )
                    holder.viewBinding.weatherState3.setText(
                        city.citySpecificData!!.consolidated_weather.get(2)
                            .weather_state_name
                    )
                    holder.viewBinding.weatherTemp3.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(2)
                                .min_temp
                        ).toString() + "°C"
                    )
                    holder.viewBinding.weatherTempMax3.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(2)
                                .max_temp
                        ).toString() + "°C"
                    )
                    holder.viewBinding.weatherWint3.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(2)
                                .wind_speed
                        ).toString() + "mph"
                    )
                    Glide.with(holder.viewBinding.weatherImgDay4)
                        .load(
                            "https://www.metaweather.com/static/img/weather/png/" + city.citySpecificData!!
                                .consolidated_weather.get(3).weather_state_abbr
                                .toString() + ".png"
                        )
                        .into(holder.viewBinding.weatherImgDay4)
                    holder.viewBinding.weatherDate4.setText(
                        city.citySpecificData!!.consolidated_weather.get(3)
                            .applicable_date
                    )
                    holder.viewBinding.weatherState4.setText(
                        city.citySpecificData!!.consolidated_weather.get(3)
                            .weather_state_name
                    )
                    holder.viewBinding.weatherTemp4.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(3)
                                .min_temp
                        ).toString() + "°C"
                    )
                    holder.viewBinding.weatherTempMax4.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(3)
                                .max_temp
                        ).toString() + "°C"
                    )
                    holder.viewBinding.weatherWint4.setText(
                        stringCut(
                            city.citySpecificData!!.consolidated_weather.get(3)
                                .wind_speed
                        ).toString() + "mph"
                    )

                    holder.viewBinding.timesLayout.setVisibility(View.VISIBLE)
                    holder.viewBinding.progressBar.setVisibility(View.GONE)
                }
            })

        if (withInternet) {
            GlobalScope.launch(Dispatchers.Main) {
                val result = withContext(Dispatchers.IO) {
                    this@CitysResultsAdapter.cityRepositoryImp.searchWeatherByCity(cityShow.idCity)
                }
                cityShow.citySpecificData = result
                informationCity.postValue(cityShow);
            }

        } else {
            informationCity.postValue(cityShow)
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun setResults(results: List<City>) {
        this.results = results
        notifyDataSetChanged()
    }

    inner class CitysResultsHolder(binding: CityItemBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        val viewBinding: CityItemBinding

        init {
            viewBinding = binding
        }
    }


}