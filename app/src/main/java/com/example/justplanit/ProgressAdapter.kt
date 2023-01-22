package com.example.justplanit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import java.time.format.DateTimeFormatter
import java.util.*

class ProgressAdapter(
    var fortschritte: List<Fortschritt>,
    val clickListener: (fortschritt: Fortschritt) -> Unit
): RecyclerView.Adapter<ProgressAdapter.ViewHolder>() {

    private var progresslist = fortschritte as MutableList<Fortschritt>

    inner class ViewHolder(itemView: View, val clickListener: (fortschritt: Fortschritt) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(fortschritt: Fortschritt,index:Int) {
            itemView.findViewById<TextView>(R.id.item_progress_id).text =
                fortschritt.id.toString()
            itemView.findViewById<TextView>(R.id.item_progress_activity).text =
                SqlDatabase.getDatabase(itemView.context).getSqlData.selAktivitaet(fortschritt.aktivitaet)
            itemView.findViewById<TextView>(R.id.item_progress_amount).text =
                fortschritt.zielmenge.toString()
            itemView.findViewById<TextView>(R.id.item_progress_metric).text = " " +
                SqlDatabase.getDatabase(itemView.context).getSqlData.selMetrik(fortschritt.metrik)
            itemView.findViewById<TextView>(R.id.item_progress_date).text =
                Converter().dateToString(fortschritt.datum)

            itemView.findViewById<ImageView>(R.id.item_progress_delete).setOnClickListener {
                clickListener(fortschritt)
                deleteItem(index)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val progressItemView = inflater.inflate(R.layout.item_progress, parent, false)
        return ViewHolder(progressItemView, clickListener)
    }

    override fun getItemCount(): Int {
        return progresslist.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, postion: Int) {
        holder.bindItem(progresslist[postion],postion)
    }

    fun deleteItem(pos:Int){
        progresslist.removeAt(pos)
        notifyItemRemoved(pos)
        notifyDataSetChanged()
    }

}