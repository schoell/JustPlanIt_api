package com.example.justplanit

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


class Converter {
    //TODO - Gibt er bei falsch wirklich null raus?
        @TypeConverter
        fun stringToDate(date: String): Date? {
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN)
        return try {
            formatter.parse(date)
        } catch(e: java.lang.Exception) {
            null
        }
        }

        @TypeConverter
        fun dateToString(date: Date) : String {
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN)
            return format.format(date)
        }

    fun bitmapToBytes(bitmap:Bitmap):ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.PNG,0,stream)
        return stream.toByteArray()
    }

    fun bytesToBitmap(image: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }

}