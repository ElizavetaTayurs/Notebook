package com.example.notebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(){
    private var stdList: ArrayList<PersonModel> = ArrayList()
    private var onClickItem: ((PersonModel) -> Unit)? = null
    private var onClickDeleteItem: ((PersonModel) -> Unit)? = null

    fun addItems(items: ArrayList<PersonModel>) {
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (PersonModel) -> Unit) {
        this.onClickItem = callback
    }

    fun setOnClickDeleteItem(callback: (PersonModel) -> Unit) {
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.activity_create, parent, false)
    )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener { onClickItem?.invoke(std) }
        holder.buttonDelete.setOnClickListener { onClickDeleteItem?.invoke(std) }
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    class PersonViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvName)
        private var birthday = view.findViewById<TextView>(R.id.tvBirthday)
        private var phone = view.findViewById<TextView>(R.id.tvPhone)
        var buttonDelete = view.findViewById<TextView>(R.id.buttonDelete)

        fun bindView(std: PersonModel) {
            id.text = std.id.toString()
            name.text = std.name
            birthday.text = std.birthday
            phone.text = std.phone
        }
    }

}