package com.example.recyclerviewproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_layout.view.*

class recycler_view_adapter(val items : List<RecyclerItems>) : RecyclerView.Adapter<recycler_view_adapter.RecyclerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.content_layout, parent ,false)

        return RecyclerHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.title.text = items[position].title
        holder.description.text = items[position].description
        holder.imageView.setImageResource(items[position].image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.title
        val description : TextView = itemView.description
        val imageView : ImageView = itemView.image
    }

}