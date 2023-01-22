package com.example.justplanit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Aktivitaet::class, Fortschritt::class, Vorsatz::class, Metrik::class, Intervall::class, Achievement::class], version = 1)
@TypeConverters(Converter::class)
abstract class SqlDatabase : RoomDatabase() {
    abstract val getSqlData: GetSqlData

    companion object {
        private var INSTANCE: SqlDatabase? = null
        fun getDatabase(context: Context): SqlDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): SqlDatabase {
            return Room.databaseBuilder(
                context,
                SqlDatabase::class.java, "sql-db"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }
}