package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide

class Rnewsadapter (val context: Context, val Articles:List<Article> ) : Adapter<Rnewsadapter.Rviewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Rviewholder {
        val layoutinflator=LayoutInflater.from(parent.context)
        val view =layoutinflator.inflate(R.layout.item_layout,parent,false)
        return Rviewholder(view)
    }

    override fun onBindViewHolder(holder: Rviewholder, position: Int) {
        holder.newstitle.text=Articles[position].title
        holder.description.text=Articles[position].description
        Glide.with(context).load(Articles[position].urlToImage).into(holder.newsimage)
        holder.itemView.setOnClickListener{

            val intent = Intent(context,webview::class.java)
            intent.putExtra("URL",Articles[position].url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return Articles.size
    }

    class Rviewholder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val newsimage=itemView.findViewById<ImageView>(R.id.newsimage)
        val newstitle=itemView.findViewById<TextView>(R.id.titleview)
        val description=itemView.findViewById<TextView>(R.id.description)
    }
}
