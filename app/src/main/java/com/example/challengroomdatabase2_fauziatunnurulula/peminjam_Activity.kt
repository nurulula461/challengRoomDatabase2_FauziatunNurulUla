package com.example.challengroomdatabase2_fauziatunnurulula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challengroomdatabase2_fauziatunnurulula.Room.dbPerpus
import com.example.challengroomdatabase2_fauziatunnurulula.Room.tbPinjam
import com.example.challengroomdatabase2_fauziatunnurulula.Room.tb_buku
import kotlinx.android.synthetic.main.activity_edit_perpus.*
import kotlinx.android.synthetic.main.activity_edit_perpus.bt_simpan
import kotlinx.android.synthetic.main.activity_peminjam.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class peminjam_Activity : AppCompatActivity() {

    val db by lazy { dbPerpus(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peminjam)
        simpanPinjam()
    }

    fun simpanPinjam() {
        bt_simpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbcarryDao().addPinjam(
                    tbPinjam(
                        et_pinjam.text.toString().toInt(),
                        et_nama.text.toString(),
                        et_judPinjam.text.toString(),
                        et_jumPinjam.text.toString(),
                        et_tglPinjam.text.toString(),
                        et_pengembalian.text.toString())
                )
                finish()
            }
        }
    }
}