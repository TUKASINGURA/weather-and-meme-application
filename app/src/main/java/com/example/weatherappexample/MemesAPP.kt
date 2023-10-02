package com.example.weatherappexample

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.weatherappexample.Data.data.memes
import com.example.weatherappexample.DataAdapter.RvAdapter
import com.example.weatherappexample.Utils.RetrofitInstance
import com.example.weatherappexample.databinding.ActivityMemesAppBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemesAPP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMemesAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMemes()
    }

    private fun getMemes() {
        GlobalScope.launch(Dispatchers.IO){
            val responseMemes=RetrofitInstance().createMemeRetrofit().getMemes()

            withContext(Dispatchers.Main){
                withContext(Dispatchers.Main){
                    val memesList: List<memes> = responseMemes.body()!!.data.memes //response.body()!!.data.memes
                    val progressBar= findViewById<ProgressBar>(R.id.progressBar)
                    progressBar.visibility= View.GONE

                    val rvAdapter =RvAdapter(memesList)
                    // implementation of the REcyclerView
                    val recyclerView= findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.adapter= rvAdapter

                    recyclerView.layoutManager= StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
            }
        }
    }
    /**
        GlobalScope.launch(Dispatchers.IO) {
        val response = try {
        RetrofitInstance.api.getMemes()

        }catch (e: IOException){
        Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_SHORT).show()
        return@launch
        }catch (e: HttpException){
        Toast.makeText(applicationContext, "http error: ${e.message}", Toast.LENGTH_SHORT)
        .show()
        return@launch
        }
        if (response.isSuccessful && response.body() != null){
        withContext(Dispatchers.Main){
        val memesList: List<Meme> = response.body()!!.data.memes
        binding.apply {
        progressBar.visibility = View.GONE
        rvAdapter = RvAdapter(memesList)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
        }*/

}}