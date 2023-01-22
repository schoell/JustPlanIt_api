package com.example.justplanit.ui.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.justplanit.Fortschritt
import com.example.justplanit.ProgressAdapter
import com.example.justplanit.R
import com.example.justplanit.SqlDatabase
import androidx.room.Room
import com.example.justplanit.*
import com.example.justplanit.databinding.FragmentProgressBinding
import org.w3c.dom.Text
import java.util.*


class ProgressFragment : Fragment()  {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val dashboardViewModel =
            ViewModelProvider(this)[ProgressViewModel::class.java]

        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        val root: View = binding.root


        setAdapter(root.findViewById(R.id.progress_recycler_view))

        root.findViewById<TextView>(R.id.progress_date).text = Converter().dateToString(Date())

        root.findViewById<Button>(R.id.progress_add).setOnClickListener {
            if(Converter().stringToDate(root.findViewById<TextView>(R.id.progress_date).text.toString()) == null) {
                Toast.makeText(requireContext().applicationContext,"Falsches Datum! Richtiges Format: yyyy-mm-dd",Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(
                    requireContext().applicationContext,
                    "A new progress was created",
                    Toast.LENGTH_SHORT
                ).show()
                SqlDatabase.getDatabase(requireContext().applicationContext).getSqlData.insFortschritt(
                    Fortschritt(
                        datum = Converter().stringToDate(root.findViewById<TextView>(R.id.progress_date).text.toString()) ?: Date(),
                        aktivitaet = SqlDatabase.getDatabase(requireContext().applicationContext).getSqlData.selAktivitaet(
                            root.findViewById<Spinner>(R.id.progress_activity).selectedItem.toString()
                        ),
                        metrik = SqlDatabase.getDatabase(requireContext().applicationContext).getSqlData.selMetrik(
                            root.findViewById<Spinner>(R.id.progress_unit).selectedItem.toString()
                        ),
                        zielmenge = root.findViewById<TextView>(R.id.progress_amount).text.toString()
                            .toIntOrNull() ?: 0
                    )
                )
                setAdapter(root.findViewById(R.id.progress_recycler_view))
            }
        }

        //Um den Aktvität-Spinner aufzufüllen
        root.findViewById<Spinner>(R.id.progress_activity).adapter = ArrayAdapter(
            requireContext().applicationContext,
            android.R.layout.simple_spinner_item,
            SqlDatabase.getDatabase(requireContext().applicationContext).getSqlData.selAktivitaet().map { it.bezeichnung })


        //Um den Metrik-Spinner aufzufüllen
        root.findViewById<Spinner>(R.id.progress_unit).adapter = ArrayAdapter(
            requireContext().applicationContext,
            android.R.layout.simple_spinner_item,
            SqlDatabase.getDatabase(requireContext().applicationContext).getSqlData.selMetrik().map { it.einheit })

        return root
    }

    //Um den RecyclerView zu setzten
    private fun setAdapter(recyclerView: RecyclerView){
        //Um den Fortschritt zu löschen
        val progressAdapter = ProgressAdapter(SqlDatabase.getDatabase(requireContext().applicationContext).getSqlData.selFortschritte()) {
            SqlDatabase.getDatabase(requireContext().applicationContext).getSqlData.delFortschritt(it.id.toString())
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = progressAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}