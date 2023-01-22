package com.example.justplanit.ui.home

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.justplanit.*
import com.example.justplanit.databinding.FragmentHomeBinding
import com.squareup.moshi.Moshi

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null

    companion object {
        val RESOLUTION_ID = "RESOLUTION_ID"
        val RESOLUTION_VIEW = 1
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Button for creating a new resolution
        val button: Button = root.findViewById(R.id.home_resolution_create)
        button.setOnClickListener {
            val intent = Intent(activity, CreateResolutionActivity::class.java)
            activity?.startActivity(intent)
        }

        val motd: TextView = root.findViewById(R.id.home_motd)
        MessageRepository.messageList(
            success = {
                motd.text = it[0].advice
            },
            error = {
                Log.w("Error", it)
                val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1
                motd.text = MessageRepository.messages[day].advice
            }
        )

        setAdapter(root.findViewById(R.id.home_recycler_view))

        parseJson()

        return root
    }

    override fun onResume() {
        activity?.let { setAdapter(it.findViewById(R.id.home_recycler_view)) }
        super.onResume()
    }

    //Um den RecyclerView zu setzten
    private fun setAdapter(recyclerView: RecyclerView){
        //Um den Vorsatz anzuklicken
        val resolutionAdapter = ResolutionAdapter(SqlDatabase.getDatabase(requireContext().applicationContext).getSqlData.selVorsatz()) {
            val intent = Intent(activity, ViewResolutionActivity::class.java)
            intent.putExtra(RESOLUTION_ID, it.id)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = resolutionAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun parseJson() {
        val json = """
            {
                "slip_id": "118",
                "advice": "A common regret in life is wishing one had the courage to be ones true self."
            }
        """
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<slip>(slip::class.java)
        val advice = jsonAdapter.fromJson(json)
    }
}