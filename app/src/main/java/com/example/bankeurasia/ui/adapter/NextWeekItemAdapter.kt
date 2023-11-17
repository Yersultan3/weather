package com.example.bankeurasia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bankeurasia.databinding.ItemNextWeekInfoBinding
import com.example.bankeurasia.model.WeatherList
import com.example.bankeurasia.utils.load
import com.example.bankeurasia.utils.url
import com.example.bankeurasia.utils.urlSize
import java.text.SimpleDateFormat
import java.util.Locale


class NextWeekItemAdapter :
    ListAdapter<WeatherList, NextWeekItemAdapter.NextWeekItemViewHolder>(NextWeekItemMainDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextWeekItemViewHolder {
        return NextWeekItemViewHolder(
            ItemNextWeekInfoBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NextWeekItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NextWeekItemViewHolder(
        private val binding: ItemNextWeekInfoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private var item: WeatherList? = null

        fun bind(item: WeatherList) {
            this.item = item
            binding.iconTemp.load(url + item.weather.firstOrNull()?.icon + urlSize)
            binding.tvNameDay.text = getDayOfWeek(item.dtTxt)
            binding.tvMaxTemp.text = item.main.temp.toString()
            binding.tvMinTemp.text = item.main.tempMin.toInt().toString()
            binding.tvDate.text = item.dtTxt
        }

        private fun getDayOfWeek(dateString: String): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return try {
                val date = dateFormat.parse(dateString)
                val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale.getDefault())
                dayOfWeekFormat.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
                "Unknown"
            }
        }
    }

}

class NextWeekItemMainDiff : DiffUtil.ItemCallback<WeatherList>() {
    override fun areItemsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
        return oldItem.main == newItem.main
    }
}