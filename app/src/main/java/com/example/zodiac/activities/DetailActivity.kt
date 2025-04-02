package com.example.zodiac.activities

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zodiac.R
import com.example.zodiac.data.Horoscope
import com.example.zodiac.data.Horoscope_provider
import com.example.zodiac.utils.SessionManager

class DetailActivity : AppCompatActivity() {

    lateinit var nameTextView: TextView
    lateinit var datesTextView: TextView
    lateinit var horoscopeImageView: ImageView


    lateinit var session: SessionManager
    lateinit var  horoscope: Horoscope
    var isFAvorite = false
    lateinit var  favoriteMenuITem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        session = SessionManager(this)

        nameTextView = findViewById(R.id.nameTextView)
        datesTextView = findViewById(R.id.datesTextView)
        horoscopeImageView = findViewById(R.id.horoscopeImageView)

        val id = intent.getStringExtra("HOROSCOPE_IDs")!!

        horoscope = Horoscope_provider.getById(id)!!

        isFAvorite = session.getFavoriteHoroscope() == horoscope.id


        nameTextView.setText(horoscope.name)
        datesTextView.setText(horoscope.date)
        horoscopeImageView.setImageResource(horoscope.icon)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detail, menu)

        favoriteMenuITem = menu.findItem(R.id.favorite)
        setFavoriteIcon()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                if (isFAvorite) {
                    session.setFavoriteHoroscope("")
                } else {
                    session.setFavoriteHoroscope(horoscope.id)
                }
                isFAvorite = !isFAvorite
                setFavoriteIcon()

                return true
            }
            R.id.menu_share -> {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    fun setFavoriteIcon() {
        if (isFAvorite) {
            favoriteMenuITem.setIcon(R.drawable.ic_favorite_selected)
    } else { favoriteMenuITem.setIcon(R.drawable.ic_favorite)
        }
    }
}