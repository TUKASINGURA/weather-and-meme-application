package com.example.weatherappexample.DataAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappexample.Data.data.memes
import com.example.weatherappexample.databinding.RvItemBinding
import com.squareup.picasso.Picasso

class RvAdapter(private val memeList: List<memes>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    class ViewHolder(val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return memeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = memeList[position]
        holder.binding.apply {
            textView.text = currentItem.name
            Picasso.get().load(currentItem.url).into(imageView)
        }
    }
}