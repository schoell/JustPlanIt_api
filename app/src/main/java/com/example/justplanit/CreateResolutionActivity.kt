package com.example.justplanit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.example.justplanit.R
import org.w3c.dom.Text
import java.util.*

class CreateResolutionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_resolution)

        findViewById<TextView>(R.id.resolution_date).text = Converter().dateToString(Date())

        findViewById<Button>(R.id.resolution_save).setOnClickListener {
            if(Converter().stringToDate(findViewById<TextView>(R.id.resolution_date).text.toString()) == null) {
                Toast.makeText(applicationContext,"Falsches Datum! Richtiges Format: yyyy-mm-dd",Toast.LENGTH_LONG).show()
            }else{
            Toast.makeText(applicationContext,"A new resolution was created", Toast.LENGTH_SHORT).show()
            SqlDatabase.getDatabase(applicationContext).getSqlData.insVorsatz(
                Vorsatz(
                    bezeichnung = findViewById<TextView>(R.id.resolution_name).text.toString(),
                    intervall = SqlDatabase.getDatabase(applicationContext).
                        getSqlData.selIntervall(findViewById<Spinner>(R.id.resolution_frequency).selectedItem.toString()),
                    aktivitaet = SqlDatabase.getDatabase(applicationContext).
                        getSqlData.selAktivitaet(findViewById<Spinner>(R.id.resolution_activty).selectedItem.toString()),
                    startdatum = Converter().stringToDate(findViewById<TextView>(R.id.resolution_date).text.toString()) ?: Date(),
                    zielmenge = findViewById<TextView>(R.id.resolution_goal).text.toString().toIntOrNull() ?: 0,
                    metrik = SqlDatabase.getDatabase(applicationContext).
                        getSqlData.selMetrik(findViewById<Spinner>(R.id.resolution_unit).selectedItem.toString()),
                    aktiv = true
                )
            )
            finish()
            }
        }

        findViewById<Button>(R.id.resolution_new_activity_create).setOnClickListener {
            val activity = findViewById<TextView>(R.id.resolution_new_activity).text.toString()
            if (activity.isNotEmpty()) {
                SqlDatabase.getDatabase(this).getSqlData.insAktivitaet(Aktivitaet(bezeichnung = activity))
                findViewById<EditText>(R.id.resolution_new_activity).onEditorAction(EditorInfo.IME_ACTION_DONE)
                findViewById<Spinner>(R.id.resolution_activty).adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    SqlDatabase.getDatabase(applicationContext).getSqlData.selAktivitaet().map { it.bezeichnung })
                findViewById<TextView>(R.id.resolution_new_activity).text = ""
            }
        }

        //Um den Aktvität-Spinner aufzufüllen
        findViewById<Spinner>(R.id.resolution_activty).adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item,
            SqlDatabase.getDatabase(applicationContext).getSqlData.selAktivitaet().map { it.bezeichnung })


        //Um den Metrik-Spinner aufzufüllen
        findViewById<Spinner>(R.id.resolution_unit).adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item,
            SqlDatabase.getDatabase(applicationContext).getSqlData.selMetrik().map { it.einheit })

        //Um den Metrik-Spinner aufzufüllen
        findViewById<Spinner>(R.id.resolution_frequency).adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item,
            SqlDatabase.getDatabase(applicationContext).getSqlData.selIntervall().map { it.anzahl.toString() + " " + it.bezeichnung })
    }
}