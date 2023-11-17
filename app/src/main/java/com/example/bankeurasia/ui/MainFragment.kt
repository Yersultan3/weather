package com.example.bankeurasia.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.bankeurasia.R
import com.example.bankeurasia.base.BaseFragment
import com.example.bankeurasia.databinding.FragmentMainBinding
import com.example.bankeurasia.model.WeatherData
import com.example.bankeurasia.ui.adapter.MainItemAdapter
import com.example.bankeurasia.utils.load
import com.example.bankeurasia.utils.setSupportActionBar
import com.example.bankeurasia.utils.show
import com.example.bankeurasia.utils.url
import com.example.bankeurasia.utils.urlSize
import com.example.bankeurasia.viewmodel.WeatherViewModel
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter
import dev.chrisbanes.insetter.windowInsetTypesOf

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private val binding: FragmentMainBinding by viewBinding()

    override val layoutId: Int = R.layout.fragment_main

    private val viewModel: WeatherViewModel by viewModels()

    private val adapter = MainItemAdapter()

    private var data: WeatherData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(binding.toolbar, navigationIcon = null)
        val argString = arguments?.getString("argString")
        binding.toolbar.title = argString
        viewModel.getHourlyForecast(argString)
        binding.recyclerView.adapter = adapter
        binding.nextWeekDaysTextView.setOnClickListener {
            openNextWeekBottomView(data)
        }
    }

    private fun openNextWeekBottomView(data: WeatherData?) {
        val bottomFragment = NextWeekWeatherBottomFragment.newInstance(data)
        bottomFragment.show(this)
    }

    override fun initObservers() {
        observe(viewModel.weatherData) {
            setData(it.getOrNull())
            adapter.submitList(it.getOrNull()?.list)
            data = it.getOrNull()
        }
    }

    override fun applyInsets() {
        Insetter.builder().padding(windowInsetTypesOf(navigationBars = true))
            .applyToView(binding.root)
    }

    private fun setData(result: WeatherData?) = with(binding) {
        weatherInfoCard.apply {
            feelsTextView.text = result?.list?.firstOrNull()?.main?.feelsLike.toString()
            windSpeedTextView.text = result?.list?.firstOrNull()?.wind?.speed.toString()
            pressureTextView.text =
                result?.list?.firstOrNull()?.main?.pressure.toString()
            humidityTextView.text =
                result?.list?.firstOrNull()?.main?.humidity.toString()
            sunriseTextView.text = result?.city?.sunrise.toString()
            sunsetTextView.text = result?.city?.sunset.toString()
        }
        temp.text = result?.list?.firstOrNull()?.main?.temp.toString()
        weatherImageView.load(url + result?.list?.firstOrNull()?.weather?.firstOrNull()?.icon + urlSize)
        description.text = result?.list?.firstOrNull()?.weather?.firstOrNull()?.description
        cityName.text = result?.city?.name.toString()
    }

}