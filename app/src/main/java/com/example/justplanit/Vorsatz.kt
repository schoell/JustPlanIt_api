package com.example.justplanit

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.Date

@Entity
class Vorsatz (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @NotNull val bezeichnung: String,
    @NotNull val aktivitaet: Int,
    @NotNull val intervall: Int,
    @NotNull val startdatum: Date = Date(),
    @NotNull val zielmenge: Int,
    @NotNull val metrik: Int,
    @NotNull val aktiv: Boolean = true,
    val kommentar: String = "space for comment"
    ){
}