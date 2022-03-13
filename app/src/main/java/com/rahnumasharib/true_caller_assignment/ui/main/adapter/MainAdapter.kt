package com.rahnumasharib.true_caller_assignment.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rajkumarrajan.mvvm_architecture.R
import javax.inject.Inject

//----------------------------------------------------------------------------------------------
// Adapter class for each 10 element display
//----------------------------------------------------------------------------------------------
class MainAdapter @Inject constructor(val eachTenthElement: ArrayList<Char>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_tenth_element_item, parent, false))

    override fun getItemCount() = eachTenthElement.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.index.text = ((position+1) *  10).toString()
        holder.element.text = eachTenthElement.get(position).toString()
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
           val index = itemView.findViewById<TextView>(R.id.index)
           val element = itemView.findViewById<TextView>(R.id.element)
    }

}