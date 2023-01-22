package com.example.justplanit

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery


@Dao
interface GetSqlData {

    //Aktivität
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insAktivitaet(aktivitaet: Aktivitaet)

    @Query("SELECT id FROM Aktivitaet WHERE Bezeichnung=:string LIMIT 1")
    fun selAktivitaet(string:String): Int

    @Query("SELECT bezeichnung FROM Aktivitaet WHERE id=:id LIMIT 1")
    fun selAktivitaet(id:Int): String

    @Query("SELECT * FROM Aktivitaet")
    fun selAktivitaet(): List<Aktivitaet>

    //Fortschritt
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insFortschritt(fortschritt: Fortschritt)

    @Query("DELETE FROM Fortschritt WHERE ID=:id")
    fun delFortschritt(id:String)

    @Query("SELECT * FROM Fortschritt ORDER BY id DESC")
    fun selFortschritte(): List<Fortschritt>

    @Query("SELECT * FROM Fortschritt WHERE metrik=:metrik AND aktivitaet=:aktivitaet ORDER BY id DESC")
    fun selFortschritte(metrik:Int, aktivitaet:Int): List<Fortschritt>

    //Metrik
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insMetrik(metrik: Metrik)

    @Query("SELECT id FROM Metrik WHERE einheit=:string LIMIT 1")
    fun selMetrik(string:String): Int

    @Query("SELECT einheit FROM Metrik WHERE id=:id LIMIT 1")
    fun selMetrik(id:Int): String

    @Query("SELECT * FROM Metrik")
    fun selMetrik():  List<Metrik>

    //Vorsatz
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insVorsatz(vorsatz: Vorsatz)

    @Query("UPDATE Vorsatz SET aktiv = 0 WHERE ID=:id")
    fun delVorsatz(id: Int)

    @Query("SELECT * FROM Vorsatz WHERE aktiv = 1")
    fun selVorsatz():  List<Vorsatz>

    @Query("SELECT * FROM Vorsatz WHERE id=:id LIMIT 1")
    fun selVorsatz(id:Int): Vorsatz

    @Query("UPDATE Vorsatz SET kommentar=:note WHERE id=:id")
    fun updVorsatzNote(id:Int,note:String)

    //Intervall
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insIntervall(intervall: Intervall)

    @Query("SELECT * FROM Intervall")
    fun selIntervall():  List<Intervall>

    @Query("SELECT id FROM Intervall WHERE anzahl || ' ' || bezeichnung =:string LIMIT 1")
    fun selIntervall(string:String): Int

    @Query("SELECT anzahl || ' ' || bezeichnung  FROM Intervall WHERE id=:id LIMIT 1 ")
    fun selIntervall(id:Int): String

    //Achievement
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insAchievemnt(achievement: Achievement)

    @Query("SELECT * FROM Achievement")
    fun selAchievement(): List<Achievement>

    @Query("SELECT * FROM Achievement WHERE name=:name LIMIT 1 ")
    fun selAchievementName(name:String): Achievement

    @Query("UPDATE Achievement SET bild=:img WHERE name=:name")
    fun updAchievementImg(name:String, img:ByteArray)

    @Query("UPDATE Achievement SET kommentar=:note WHERE name=:name")
    fun updAchievementNote(name:String,note:String)

    @RawQuery
    fun rawAchievement(query: SupportSQLiteQuery): Int
}