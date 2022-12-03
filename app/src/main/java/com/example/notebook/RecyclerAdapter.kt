/*package com.example.notebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    val list: List<String>,
    val clickHandler: (index: Int) -> Unit
): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_items,parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position]
        holder.buttonDelete.setOnClickListener {
        clickHandler(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val buttonDelete = itemView.findViewById<Button>(R.id.buttonDelete)
    }
}*/