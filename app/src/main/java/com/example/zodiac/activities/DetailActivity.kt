package com.example.zodiac.activities

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetailActivity : AppCompatActivity() {

    lateinit var nameTextView: TextView
    lateinit var datesTextView: TextView
    lateinit var horoscopeImageView: ImageView


    lateinit var session: SessionManager
    lateinit var  horoscope: Horoscope
    var isFAvorite = false
    lateinit var  favoriteMenuITem: MenuItem
    lateinit var horoscopeLuckTextView: TextView
    lateinit var progressBar: LinearProgressIndicator
    lateinit var navigationBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        session = SessionManager(this)

        nameTextView = findViewById(R.id.nameTextView)
        datesTextView = findViewById(R.id.datesTextView)
        horoscopeImageView = findViewById(R.id.horoscopeImageView)
        horoscopeLuckTextView= findViewById(R.id.horoscopeLuckTextView)
        progressBar = findViewById(R.id.progressBar)
        navigationBar = findViewById(R.id.navigationBar)

        val id = intent.getStringExtra("HOROSCOPE_IDs")!!

        horoscope = Horoscope_provider.getById(id)!!

        isFAvorite = session.getFavoriteHoroscope() == horoscope.id

        supportActionBar?.setTitle(horoscope.name)
        supportActionBar?.setSubtitle(horoscope.date)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        nameTextView.setText(horoscope.name)
        datesTextView.setText(horoscope.date)
        horoscopeImageView.setImageResource(horoscope.icon)

        navigationBar.setOnItemSelectedListener {  menuItem ->
            val period = when (menuItem.itemId){
                R.id.menu_daily -> "daily"
                R.id.menu_weekly -> "weekly"
                R.id.menu_monthly -> "monthly"
                else -> "daily"
            }
            getHoroscopeLuck(period)
            true
        }

        getHoroscopeLuck()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detail, menu)

        favoriteMenuITem = menu.findItem(R.id.favorite)
        setFavoriteIcon()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
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

    fun getHoroscopeLuck(period:String = "daily") {
        progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/${period}?sign=${horoscope.id}")

            // HTTP Connexion
            val urlConnection = url.openConnection() as HttpsURLConnection

            // Method
            urlConnection.requestMethod = "GET"

            try {
                if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    // Read the response
                    val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val response = StringBuffer()
                    var inputLine: String? = null

                    while ((bufferedReader.readLine().also { inputLine = it }) != null) {
                        response.append(inputLine)
                    }
                    bufferedReader.close()

                    val result = JSONObject(response.toString()).getJSONObject("data").getString("horoscope_data")

                    CoroutineScope(Dispatchers.Main).launch {
                        progressBar.visibility = View.GONE
                        horoscopeLuckTextView.text = result
                    }
                } else {
                    Log.i("API", "Hubo un error en la llamada al API")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }
        }
    }
}