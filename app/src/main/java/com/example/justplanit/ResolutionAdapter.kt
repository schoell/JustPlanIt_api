package com.example.justplanit

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ResolutionAdapter(val resolutionList: List<Vorsatz>, val clickListener: (res: Vorsatz) -> Unit): RecyclerView.Adapter<ResolutionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val clickListener: (res: Vorsatz) -> Unit):RecyclerView.ViewHolder(itemView){
        fun bindItem(resolution:Vorsatz){
            itemView.findViewById<TextView>(R.id.item_resolution_activty).text = resolution.bezeichnung
            itemView.findViewById<TextView>(R.id.item_resolution_intervall).text =
                SqlDatabase.getDatabase(itemView.context).getSqlData.selIntervall(resolution.intervall)


            itemView.setOnClickListener{
                clickListener(resolution)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val resolutionItemView = inflater.inflate(R.layout.item_resolution, parent, false)
        return ViewHolder(resolutionItemView, clickListener)
    }

    override fun getItemCount(): Int {
        return resolutionList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resolution = resolutionList[position]
        holder.bindItem(resolution)
    }
}