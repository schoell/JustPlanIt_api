package com.example.justplanit

import android.content.Context
import java.util.*

class ExampleData(var context: Context) {

    fun create() {
        if(SqlDatabase.getDatabase(context).getSqlData.selAktivitaet().isNotEmpty()){return}

        aktivitaet("Gesund Essen")
        aktivitaet("Handy Fasten")
        aktivitaet("Laufen")
        aktivitaet("Lernen")
        aktivitaet("Meditieren")
        aktivitaet("Trainieren")
        aktivitaet("Trinken")
        aktivitaet("Wandern")

        metrik("Meter", "m")
        metrik("Kilometer", "km")
        metrik("Liter", "l")
        metrik("Sekunden", "s")
        metrik("Minuten", "min")
        metrik("Stunden", "h")
        metrik("Male", "X")

        fortschritt("2022-10-10", 3, 10, 1)
        fortschritt("2022-10-11", 8, 11, 2)
        fortschritt("2022-10-12", 7, 12, 3)
        fortschritt("2022-10-12", 2, 20, 5)
        fortschritt("2022-10-12", 5, 1, 6)
        fortschritt("2022-10-12", 1, 5, 7)

        vorsatz("Fit werden",1,3,"2022-12-01", 100, 1, true)
        vorsatz("Richtig Fit werden",2,3,"2022-12-01", 200, 2, true)
        vorsatz("Hammer werden",5,3,"2022-02-01", 150, 7, true)

        intervall("Stündlich",1)
        intervall("Täglich",1)
        intervall("Täglich",2)
        intervall("Wöchentlich",1)
        intervall("Wöchentlich",2)
        intervall("Wöchentlich",3)
        intervall("Monatlich",1)
        intervall("Monatlich",5)
        intervall("Monatlich",10)

        achievement("Created a progress",Date(),"SELECT COUNT(*) FROM Fortschritt")
        achievement("Created 5 progresses",Date(),"SELECT COUNT(*)-4 FROM Fortschritt")
        achievement("Created 10 progresses",Date(),"SELECT COUNT(*)-9 FROM Fortschritt")
        achievement("Create a Resolution",Date(),"SELECT COUNT(*) FROM Vorsatz")
        achievement("Create 5 Resolutions",Date(),"SELECT COUNT(*)-4 FROM Vorsatz")
        achievement("Create 10 Resolutions",Date(),"SELECT COUNT(*)-9 FROM Vorsatz")
        achievement("Drinking is healthy!",Date(),"SELECT COUNT(*) FROM Vorsatz WHERE aktivitaet=7")
    }

    fun metrik(bezeichnung:String, einheit:String){
        SqlDatabase.getDatabase(context).getSqlData.insMetrik(Metrik(bezeichnung = bezeichnung, einheit = einheit))
    }

    fun fortschritt(datum:String, aktivitaet:Int, zielmenge:Int, metrik: Int){
        SqlDatabase.getDatabase(context).getSqlData.insFortschritt(Fortschritt(datum = Converter().stringToDate(datum) ?: Date(), aktivitaet = aktivitaet, zielmenge = zielmenge, metrik = metrik))
    }

    fun aktivitaet(bezeichnung:String){
        SqlDatabase.getDatabase(context).getSqlData.insAktivitaet(Aktivitaet(bezeichnung = bezeichnung))
    }

    fun vorsatz(bezeichnung:String, intervall: Int, aktivitaet: Int, startdatum:String, zielmenge: Int, metrik: Int, aktiv:Boolean){
        SqlDatabase.getDatabase(context).getSqlData.insVorsatz(Vorsatz(bezeichnung = bezeichnung, intervall = intervall,
            aktivitaet = aktivitaet, startdatum = Converter().stringToDate(startdatum) ?: Date(), zielmenge = zielmenge, metrik = metrik, aktiv = aktiv))
    }

    fun intervall(bezeichnung:String, anzahl: Int){
        SqlDatabase.getDatabase(context).getSqlData.insIntervall(Intervall(bezeichnung = bezeichnung, anzahl = anzahl))
    }

    fun achievement(name:String, datum: Date, voraussetzung: String){
        SqlDatabase.getDatabase(context).getSqlData.insAchievemnt(Achievement(name,datum,voraussetzung))
    }
}