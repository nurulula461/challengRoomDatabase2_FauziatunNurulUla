package com.example.challengroomdatabase2_fauziatunnurulula.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tb_buku(
        @PrimaryKey
        val id_buku : Int,
        val kategori : String,
        val judul : String,
        val pengarang : String,
        val penerbit : String,
        val jumlah_buku : String
    )
