package com.mws.aplikasidatafilm.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mws.aplikasidatafilm.R
import com.mws.aplikasidatafilm.activity.updatefilm.FilmUpdate
import com.mws.aplikasidatafilm.model.FilmModel

class FilmAdapter(private val dataList: ArrayList<FilmModel>):
    RecyclerView.Adapter<FilmAdapter.viewHolderData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderData {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.film_list, parent,false)

        return viewHolderData(itemView)
    }

    override fun onBindViewHolder(holder: FilmAdapter.viewHolderData, position: Int) {

        val item = dataList[position]

        holder.judul.text = item.judul
        holder.genre.text = item.genre
        holder.asal.text = item.asal
        holder.durasi.text = item.durasi

        holder.itemView.setOnClickListener{
            Log.d("ITEM","clicked")
            val ctx = holder.context
            val intent = Intent(ctx,FilmUpdate::class.java)
            intent.putExtra("ID", item.id.toString())
            intent.putExtra("JUDUL", item.judul.toString())
            intent.putExtra("GENRE", item.genre.toString())
            intent.putExtra("ASAL", item.asal.toString())
            intent.putExtra("DURASI", item.durasi.toString())
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = dataList.size

    class viewHolderData(itemView: View): RecyclerView.ViewHolder(itemView) {

        val judul: TextView = itemView.findViewById(R.id.tv_judul)
        val genre: TextView = itemView.findViewById(R.id.tv_genre)
        val asal: TextView = itemView.findViewById(R.id.tv_asal)
        val durasi: TextView = itemView.findViewById(R.id.tv_durasi)
        val context = itemView.context

    }
}

