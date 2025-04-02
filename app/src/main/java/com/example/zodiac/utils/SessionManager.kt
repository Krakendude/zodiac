package com.example.zodiac.utils

import android.content.Context

class SessionManager (context: Context) {
    private val sharedPref = context.getSharedPreferences("zodiac session", Context.MODE_PRIVATE)

    fun setFavoriteHoroscope(id: String) {
        val editor = sharedPref.edit()
        editor.putString("Favorite_Horoscope", id)
        editor.apply()
    }

    fun getFavoriteHoroscope(): String {
        return sharedPref.getString("Favorite_Horoscope", "")!!
    }
}