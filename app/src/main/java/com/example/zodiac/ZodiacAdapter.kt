package com.example.zodiac

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.zodiac.data.Horoscope_data

class ZodiacAdapter (val items: List<Horoscope_data>): Adapter<HoroscopeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = items[position]
        holder.renderData(horoscope)
    }
}

class HoroscopeViewHolder (view: View) : ViewHolder(view) {

    val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    val datesTetView: TextView = view.findViewById(R.id.datesTextView)
    val horoscopeImageView: ImageView = view.findViewById(R.id.horoscopeImageView)

    fun renderData(horoscope: Horoscope_data) {
        nameTextView.setText(horoscope.name)
        datesTetView.setText(horoscope.date)
        horoscopeImageView.setImageResource(horoscope.icon)
    }
}