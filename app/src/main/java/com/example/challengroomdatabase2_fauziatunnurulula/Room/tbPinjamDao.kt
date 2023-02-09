package com.example.challengroomdatabase2_fauziatunnurulula.Room

import androidx.room.*

@Dao
interface tbPinjamDao {
    @Insert
    fun addPinjam (TbPinjam: tbPinjam)
    @Update
    fun updatePinjam (TbPinjam: tbPinjam)
    @Delete
    fun deletePinjam (TbPinjam: tbPinjam)
    @Query("SELECT * FROM tbPinjam")
    fun dataPinjam (): List<tbPinjam>
    @Query("SELECT * FROM tbPinjam WHERE id_bukuPinjam=:tbPinjam_id")
    fun tampilPinjam (tbPinjam_id: Int): List<tbPinjam>
}