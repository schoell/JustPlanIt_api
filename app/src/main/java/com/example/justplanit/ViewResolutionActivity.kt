package com.example.justplanit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.justplanit.ui.home.HomeFragment


class ViewResolutionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_resolution)

        val resolution = SqlDatabase.getDatabase(applicationContext).getSqlData.
            selVorsatz(intent.getIntExtra(HomeFragment.RESOLUTION_ID,0))

        findViewById<TextView>(R.id.respro_header).text = resolution.bezeichnung
        findViewById<TextView>(R.id.respro_date).text = Converter().dateToString(resolution.startdatum)
        findViewById<TextView>(R.id.respro_goal).text =
            buildString {
                append( SqlDatabase.getDatabase(applicationContext).
                    getSqlData.selAktivitaet(resolution.aktivitaet))
                append(", ")
                append(resolution.zielmenge)
                append(" ")
                append(SqlDatabase.getDatabase(applicationContext).
                    getSqlData.selMetrik(resolution.metrik))
                append("/")
                append(SqlDatabase.getDatabase(applicationContext).
                    getSqlData.selIntervall(resolution.intervall))
            }
        findViewById<TextView>(R.id.respro_note).text = resolution.kommentar

        setAdapter(findViewById(R.id.respro_recycler_view), resolution)

        findViewById<Button>(R.id.respro_end).setOnClickListener{
            SqlDatabase.getDatabase(applicationContext).getSqlData.delVorsatz(resolution.id)
            finish()
        }

        findViewById<Button>(R.id.respro_note_save).setOnClickListener{
            val note = findViewById<TextView>(R.id.respro_note).text.toString()
            SqlDatabase.getDatabase(applicationContext).getSqlData.updVorsatzNote(resolution.id,note)
        }

        findViewById<Button>(R.id.respro_add).setOnClickListener{
            //TODO - Go to progress fragement
            // aktuell habe ich den butten einfach entfernt -FLo
        }
    }



    private fun setAdapter(recyclerView: RecyclerView, resolution: Vorsatz){
        //Um den Fortschritt zu löschen
        val progressAdapter = ProgressAdapter(SqlDatabase.getDatabase(applicationContext).
            getSqlData.selFortschritte(resolution.metrik, resolution.aktivitaet)) {
            SqlDatabase.getDatabase(applicationContext).getSqlData.delFortschritt(it.id.toString())
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = progressAdapter
    }

}