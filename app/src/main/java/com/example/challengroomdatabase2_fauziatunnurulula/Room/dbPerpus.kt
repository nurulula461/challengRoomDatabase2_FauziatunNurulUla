package com.example.challengroomdatabase2_fauziatunnurulula.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [tb_buku::class,tbPinjam::class],
    version = 1
)

abstract class dbPerpus : RoomDatabase() {
    abstract  fun  tbBukuDao() : tbBukuDao
    abstract  fun  tbcarryDao() : tbPinjamDao

    companion object{
        @Volatile private  var instance : dbPerpus?= null
        private  val LOCK = Any()

        operator fun invoke (context: Context) =
         instance?: synchronized(LOCK){
            instance?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase (context: Context) = Room.databaseBuilder(
            context.applicationContext,
            dbPerpus::class.java,
            "tbsiswa12345.db"
        ).build()

    }
}