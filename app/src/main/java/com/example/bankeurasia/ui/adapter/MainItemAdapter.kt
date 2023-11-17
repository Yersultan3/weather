package com.example.bankeurasia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bankeurasia.databinding.ItemWeatherInfoBinding
import com.example.bankeurasia.model.WeatherList
import com.example.bankeurasia.utils.load
import com.example.bankeurasia.utils.url
import com.example.bankeurasia.utils.urlSize


class MainItemAdapter :
    ListAdapter<WeatherList, MainItemAdapter.MainItemViewHolder>(ItemMainDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder {
        return MainItemViewHolder(
            ItemWeatherInfoBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MainItemViewHolder(
        private val binding: ItemWeatherInfoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private var item: WeatherList? = null

        fun bind(item: WeatherList) {
            this.item = item
            binding.iconTemp.load(url + item.weather.firstOrNull()?.icon + urlSize)
            binding.tvTemp.text = item.main.temp.toString()
            binding.tvNameDay.text = item.dtTxt.substring(10)
            binding.tvTempMax.text = item.main.tempMax.toInt().toString()
            binding.tvTempMin.text = item.main.tempMin.toInt().toString()
        }
    }

}

class ItemMainDiff : DiffUtil.ItemCallback<WeatherList>() {
    override fun areItemsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
        return oldItem.main == newItem.main
    }
}