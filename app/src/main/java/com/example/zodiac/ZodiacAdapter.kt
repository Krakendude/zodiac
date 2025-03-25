package com.example.zodiac

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.zodiac.data.Horoscope_data

class ZodiacAdapter (val items: List<Horoscope_data>): Adapter<HoroscopeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}

class HoroscopeViewHolder (view: View) : ViewHolder(view) {


}