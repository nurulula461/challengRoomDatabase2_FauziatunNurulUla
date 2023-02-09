package com.example.challengroomdatabase2_fauziatunnurulula.Room

import androidx.room.*

@Dao
interface tbBukuDao {
    @Insert
    fun addBuku (tbBuku: tb_buku)
    @Update
    fun updateBuku (tbBuku: tb_buku)
    @Delete
    fun deleteBuku (tbBuku: tb_buku)
    @Query("SELECT * FROM tb_buku")
    fun  dataBuku (): List<tb_buku>
    @Query("SELECT * FROM tb_buku WHERE id_buku=:tbBuku_id")
    fun  tampilBuku (tbBuku_id: Int): List<tb_buku>
}