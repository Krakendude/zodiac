package com.example.zodiac

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.zodiac.data.Horoscope_data

class HoroscopeAdapter(val items: List<Horoscope_data>, val onItemClick: (Int) -> Unit) : Adapter<HoroscopeViewHolder>() {

    // Cual es la vista de las celdas
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)
    }

    // Cuantos elementos tengo que listar
    override fun getItemCount(): Int {
        return items.size
    }

    // Voy a mostrar la celda en la posicion indicada
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = items[position]
        holder.render(horoscope)
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }
}

class HoroscopeViewHolder(view: View) : ViewHolder(view) {

    val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    val datesTextView: TextView = view.findViewById(R.id.datesTextView)
    val horoscopeImageView: ImageView = view.findViewById(R.id.horoscopeImageView)

    fun render(horoscope: Horoscope_data) {
        nameTextView.setText(horoscope.name)
        datesTextView.setText(horoscope.date)
        horoscopeImageView.setImageResource(horoscope.icon)
    }
}