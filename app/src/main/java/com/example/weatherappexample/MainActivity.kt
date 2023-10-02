package com.example.weatherappexample


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherappexample.Utils.RetrofitInstance
import com.example.weatherappexample.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val floatingButton= binding.fab
            floatingButton.setOnClickListener{
            startActivity(Intent(this,MemesAPP::class.java))
        }



        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance().createRetrofit().getCurrentWeather(
                "Kampala",
                "metric",
                applicationContext.getString(R.string.api_key)
            )
            withContext(Dispatchers.Main) {

                val textDisplayOnScreen = findViewById<TextView>(R.id.tv_temp)
                textDisplayOnScreen.text = "${response.main.temp.toInt()}°C"

                val textDisplayOnScre = findViewById<TextView>(R.id.tv_location)
                textDisplayOnScre.text = "City ${response.name}"

                val timeUpdate = findViewById<TextView>(R.id.tv_update_time)
                timeUpdate.text = "TimeZone ${response.timezone}"

                val weatherDescription = findViewById<TextView>(R.id.tv_status)


                val minTemperature = findViewById<TextView>(R.id.tv_min_temp)
                minTemperature.text = "Min ${response.main.temp_min}°C"

                val maxTemperature = findViewById<TextView>(R.id.tv_max_temp)
                maxTemperature.text = "Max ${response.main.temp_max}°C"

                val sunset = findViewById<TextView>(R.id.tvSunset)
                sunset.text =dateFormatConverter(
                    response.sys.sunset.toLong()
                )
                val sunrise = findViewById<TextView>(R.id.tvSunrise)
                sunrise.text = dateFormatConverter(
                    response.sys.sunrise.toLong()
                )

                val windSpeed = findViewById<TextView>(R.id.tvWind)
                windSpeed.text = "${response.wind.speed}km/h"

                val pressure = findViewById<TextView>(R.id.tvPressure)
                pressure.text = "${response.main.pressure}Pa"

                val humidity = findViewById<TextView>(R.id.tvHumidity)
                humidity.text = "${response.main.humidity}%"

                //this Block of code is for changing the icon on the weather application UI FROM the weather API
                val iconId = response.weather[0].icon
                val imageWeather = findViewById<ImageView>(R.id.img_weather)
                val imgUrl = "https://openweathermap.org/img/wn/$iconId@4x.png"

                Picasso.get().load(imgUrl).into(imageWeather)
            }
/**
         =  try {

         }
             RetrofitInstance().createRetrofit().getCurrentWeather("kampala","metric",applicationContext.getString(R.string.api_key))

           }catch (e: IOException){
               Toast.makeText(applicationContext, "app Error ${e.message}" , Toast.LENGTH_LONG)
                   .show()
             return@launch
           } catch (e:HttpException){
               Toast.makeText(applicationContext,"http error ${e.message}" , Toast.LENGTH_LONG)
                   .show()
             return@launch
           }
           if (response.isSuccessful&& response.body()!=null){
               withContext(Dispatchers.Main){

                   val textDisplayOnScreen=findViewById<TextView>(R.id.tv_temp)
                  textDisplayOnScreen.text = "Pressure: ${response.body()!!.main.pressure}"
               }
           }
*/
        }
    }
/**THE foolowing function is for changing the time
 *  from milliseconds to hours to be displayed on the UI*/
    private fun dateFormatConverter(date: Long): String {

        return SimpleDateFormat(
            "hh:mm a",
            Locale.ENGLISH
        ).format(Date(date * 1000))
    }
}