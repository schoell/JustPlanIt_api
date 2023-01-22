package com.example.justplanit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.sqlite.db.SimpleSQLiteQuery


class AchievementAdapter(val achievementList: List<Achievement>, val clickListener: (res: Achievement) -> Unit): RecyclerView.Adapter<AchievementAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val clickListener: (res: Achievement) -> Unit):RecyclerView.ViewHolder(itemView){
        fun bindItem(achievement: Achievement){
            itemView.findViewById<TextView>(R.id.item_achievement_name).text = achievement.name
            itemView.findViewById<CheckBox>(R.id.item_achievement_completed).isChecked =
                 SqlDatabase.getDatabase(itemView.context).getSqlData.rawAchievement(SimpleSQLiteQuery(achievement.voraussetzung)) > 0
            if (itemView.findViewById<CheckBox>(R.id.item_achievement_completed).isChecked){
                itemView.setOnClickListener{
                    clickListener(achievement)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val achievementItemView = inflater.inflate(R.layout.item_achievement, parent, false)
        return ViewHolder(achievementItemView, clickListener)
    }

    override fun getItemCount(): Int {
        return achievementList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val achievement = achievementList[position]
        holder.bindItem(achievement)
    }
}