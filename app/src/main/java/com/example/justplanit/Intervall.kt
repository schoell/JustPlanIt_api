package com.example.justplanit

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(indices = [Index(value = ["bezeichnung", "anzahl"], unique = true)])
class Intervall (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @NotNull val bezeichnung: String,
    @NotNull val anzahl: Int) {
}