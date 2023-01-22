package com.example.justplanit.ui.achievements

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.justplanit.*
import com.example.justplanit.databinding.FragmentAchievementsBinding
import com.example.justplanit.ui.home.HomeFragment

class AchievementsFragment : Fragment() {

    companion object{
        const val ACHIEVEMENT_ID = "ACHIEVEMENT_ID"
    }

    private var _binding: FragmentAchievementsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(AchievementsViewModel::class.java)

        _binding = FragmentAchievementsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setAdapter(root.findViewById(R.id.achievements_recycler_view))

        return root
    }

    //Um den RecyclerView zu setzen
    private fun setAdapter(recyclerView: RecyclerView){
        //Um den Vorsatz anzuklicken
        val achievementAdapter = AchievementAdapter(SqlDatabase.getDatabase(requireContext().applicationContext).getSqlData.selAchievement()) {
            val intent = Intent(activity, ViewAchievementActivity::class.java)
            intent.putExtra(ACHIEVEMENT_ID, it.name)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = achievementAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}