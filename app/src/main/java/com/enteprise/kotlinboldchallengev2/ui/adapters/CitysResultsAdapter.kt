package com.enteprise.movilchallenge.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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

                    this.loadDataWeatherItem(
                        city,
                        holder.viewBinding.weatherImgDay1,
                        holder.viewBinding.weatherDate1,
                        holder.viewBinding.weatherState1,
                        holder.viewBinding.weatherTemp1,
                        holder.viewBinding.weatherTempMax1,
                        holder.viewBinding.weatherWint1,
                        0
                    )

                    this.loadDataWeatherItem(
                        city,
                        holder.viewBinding.weatherImgDay2,
                        holder.viewBinding.weatherDate2,
                        holder.viewBinding.weatherState2,
                        holder.viewBinding.weatherTemp2,
                        holder.viewBinding.weatherTempMax2,
                        holder.viewBinding.weatherWint2,
                        1
                    )


                    this.loadDataWeatherItem(
                        city,
                        holder.viewBinding.weatherImgDay3,
                        holder.viewBinding.weatherDate3,
                        holder.viewBinding.weatherState3,
                        holder.viewBinding.weatherTemp3,
                        holder.viewBinding.weatherTempMax3,
                        holder.viewBinding.weatherWint3,
                        2
                    )


                    this.loadDataWeatherItem(
                        city,
                        holder.viewBinding.weatherImgDay4,
                        holder.viewBinding.weatherDate4,
                        holder.viewBinding.weatherState4,
                        holder.viewBinding.weatherTemp4,
                        holder.viewBinding.weatherTempMax4,
                        holder.viewBinding.weatherWint4,
                        3
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


    fun loadDataWeatherItem(
        city: City,
        image: ImageView,
        date: TextView,
        weatherState: TextView,
        tempMin: TextView,
        tempMax: TextView,
        winter: TextView,
        position: Int
    ) {
        Glide.with(image)
            .load(
                "https://www.metaweather.com/static/img/weather/png/" + city.citySpecificData!!
                    .consolidated_weather.get(position).weather_state_abbr
                    .toString() + ".png"
            )
            .into(image)
        date.setText(
            city.citySpecificData!!.consolidated_weather.get(position)
                .applicable_date
        )
        weatherState.setText(
            city.citySpecificData!!.consolidated_weather.get(position)
                .weather_state_name
        )
        tempMin.setText(
            stringCut(
                city.citySpecificData!!.consolidated_weather.get(position)
                    .min_temp
            ).toString() + "°C"
        )
        tempMax.setText(
            stringCut(
                city.citySpecificData!!.consolidated_weather.get(position)
                    .max_temp
            ).toString() + "°C"
        )
        winter.setText(
            stringCut(
                city.citySpecificData!!.consolidated_weather.get(position)
                    .wind_speed
            ).toString() + "mph"
        )
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