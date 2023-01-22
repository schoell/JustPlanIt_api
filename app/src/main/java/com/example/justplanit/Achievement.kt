package com.example.justplanit

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.Date

@Entity
class Achievement(
    @PrimaryKey val name:String,
    @NotNull val datum: Date = Date(),
    val voraussetzung:String,
    val bild:ByteArray = Converter().bitmapToBytes(Bitmap.createBitmap(320,320,Bitmap.Config.ARGB_8888)),
    val kommentar:String = "space for comment"
    ) {
}