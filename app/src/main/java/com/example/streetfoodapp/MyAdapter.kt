package com.example.streetfoodapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private val foodList : ArrayList<Food>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    var onItemClick : ((Food) -> Unit)? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = foodList[position]
        holder.titleImage.setImageResource(currentItem.imageTitle)
        holder.name.text = currentItem.name
        holder.prix.text = currentItem.prix.toString()

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titleImage : ShapeableImageView = itemView.findViewById((R.id.title_image))
        val name : TextView = itemView.findViewById(R.id.name)
        val prix : TextView = itemView.findViewById(R.id.detailprix)
    }
}