package com.example.weatherappexample.Data.data

data class currentWeather(
    val coord: coord,
  val weather: ArrayList<weather>,
    val base:String ,
    val main:main,
    val visibility:Int,
    val wind:wind,
    val clouds: clouds,
    val dt: Int,
    val sys: sys,

   val timezone:Long,
    val id: Long,
   val name: String,
    val cod:Long


)
 data class coord(
     val lon:Double,
     val lat:Double
 )

data class weather(
   val  id:Int,
    val main:String="",
   val description:String="",
   val icon:String=""
)

data class main(

    val temp:Double,
    val feels_like:Double,
    val temp_min:Double,
    val temp_max:Double,
    val pressure:Int,
    val humidity:Int,
    val sea_level:Int,
    val grnd_level:Int
)

data class wind(

   val  speed:Double,
   val deg:Int,
   val gust:Double
)

data class clouds(
    val all:Int
)

data class sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise:Int,
     val sunset: Int

)

/**DATA CLASSES FOR IMPLEMENTATION OF THE MEME APPLICATION*/
data class memes(
    val box_count: Int,
    val captions: Int,
    val height: Int,
    val id: String,
    val name: String,
    val url: String,
    val width: Int
)

data class data(
    val memes:ArrayList<memes>
)
data class AllMemsData(
    val data: data,
    val success: Boolean
)