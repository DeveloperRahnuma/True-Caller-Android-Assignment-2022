package com.rahnumasharib.true_caller_assignment.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rajkumarrajan.mvvm_architecture.R
import javax.inject.Inject

//----------------------------------------------------------------------------------------------
// Adapter class for display number of same word occurence
//----------------------------------------------------------------------------------------------
class wordCounterAdapter @Inject constructor(val keysOfMap: List<String>, val wordCounterMap : HashMap<String,String>) : RecyclerView.Adapter<wordCounterAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_tenth_element_item, parent, false))

    override fun getItemCount() = wordCounterMap.keys.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.index.text = keysOfMap.get(position)
        holder.element.text = wordCounterMap.get(keysOfMap.get(position))
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
           val index = itemView.findViewById<TextView>(R.id.index)
           val element = itemView.findViewById<TextView>(R.id.element)
    }

}