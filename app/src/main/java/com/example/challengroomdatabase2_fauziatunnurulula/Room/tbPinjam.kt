package com.example.challengroomdatabase2_fauziatunnurulula.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tbPinjam (
@PrimaryKey
val id_bukuPinjam : Int,
val nama_peminjam : String,
val judul_pinjam : String,
val tanggal_pinjam : String,
val tanggal_pengembalian : String,
val jumlah_pinjam : String
)

