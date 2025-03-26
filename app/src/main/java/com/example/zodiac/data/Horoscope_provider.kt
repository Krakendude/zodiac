package com.example.zodiac.data

import com.example.zodiac.R

class Horoscope_provider {

    companion object {
        private val horoscopeList = listOf(
            Horoscope_data ("aries",
                R.string.horoscope_name_aries,
                R.string.horoscope_date_aries,
                R.drawable.aries_icon
            ),
            Horoscope_data("Taurus",
                R.string.horoscope_name_taurus,
                R.string.horoscope_date_taurus,
                R.drawable.taurus_icon
            ),
            Horoscope_data("Gemini",
                R.string.horoscope_name_gemini,
                R.string.horoscope_date_gemini,
                R.drawable.gemini_icon
            ),
            Horoscope_data("Cancer",
                R.string.horoscope_name_cancer,
                R.string.horoscope_date_cancer,
                R.drawable.cancer_icon
            ),
            Horoscope_data("Leo",
                R.string.horoscope_name_leo,
                R.string.horoscope_date_leo,
                R.drawable.leo_icon
            ),
            Horoscope_data("Virgo",
                R.string.horoscope_name_virgo,
                R.string.horoscope_date_virgo,
                R.drawable.virgo_icon
            ),
            Horoscope_data("Libra",
                R.string.horoscope_name_libra,
                R.string.horoscope_date_libra,
                R.drawable.libra_icon
            ),
            Horoscope_data("Sagittarius",
                R.string.horoscope_name_sagittarius,
                R.string.horoscope_date_sagittarius,
                R.drawable.sagittarius_icon
            ),
            Horoscope_data("Capricon",
                R.string.horoscope_name_capricorn,
                R.string.horoscope_date_capricorn,
                R.drawable.capricorn_icon
            ),
            Horoscope_data("Aquarius",
                R.string.horoscope_name_aquarius,
                R.string.horoscope_date_aquarius,
                R.drawable.aquarius_icon
            ),
            Horoscope_data("Pisces",
                R.string.horoscope_name_pisces,
                R.string.horoscope_date_pisces,
                R.drawable.pisces_icon
            ),
        )

        fun getAll(): List<Horoscope_data> {
            return horoscopeList
        }

        fun getById(id: String): Horoscope_data? {
            return horoscopeList.find {
                it.id == id
            }
        }

    }

}