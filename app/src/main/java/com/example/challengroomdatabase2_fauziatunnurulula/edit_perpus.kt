package com.example.challengroomdatabase2_fauziatunnurulula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.challengroomdatabase2_fauziatunnurulula.Room.constant
import com.example.challengroomdatabase2_fauziatunnurulula.Room.dbPerpus
import com.example.challengroomdatabase2_fauziatunnurulula.Room.tb_buku
import kotlinx.android.synthetic.main.activity_edit_perpus.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class edit_perpus : AppCompatActivity() {

    val db by lazy { dbPerpus(this) }
    private var tbbukuId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_perpus)
        simpanperpus()
        setupView()
        tbbukuId = intent.getIntExtra("intent_id",tbbukuId)
        Toast.makeText(this,tbbukuId.toString(), Toast.LENGTH_SHORT).show()
    }

    fun setupView(){
        val intentType = intent.getIntExtra("intent_Type",0)
        when (intentType) {
            constant.TYPE_CREATE -> {
                bt_update.visibility = View.GONE
            }
            constant.TYPE_READ -> {
                bt_simpan.visibility = View.GONE
                bt_update.visibility = View.GONE
                et_id.visibility = View.GONE
                membaca()

            }
            constant.TYPE_UPDATE -> {
                bt_simpan.visibility = View.GONE
                et_id.visibility = View.GONE
                membaca()

            }
        }
    }
    fun simpanperpus(){
        bt_simpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbBukuDao().addBuku(
                    tb_buku(et_id.text.toString().toInt(),
                        et_katgor.text.toString(),
                        et_judul.text.toString(),
                        et_jumlah.text.toString(),
                        et_pengarang.text.toString(),
                        et_penerbit.text.toString())
                )
                finish()
            }
        }
        bt_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbBukuDao().updateBuku(
                    tb_buku(tbbukuId,
                        et_katgor.text.toString(),
                        et_judul.text.toString(),
                        et_jumlah.text.toString(),
                        et_pengarang.text.toString(),
                        et_penerbit.text.toString())
                )
                finish()
            }
        }
    }
fun membaca() {
    tbbukuId = intent.getIntExtra("intent_id", 0)
    CoroutineScope(Dispatchers.IO).launch {
        val buyku = db.tbBukuDao().tampilBuku(tbbukuId)[0]
    et_judul.setText(buyku.judul)
    et_katgor.setText(buyku.kategori)
    et_jumlah.setText(buyku.jumlah_buku)
    et_penerbit.setText(buyku.penerbit)
    et_pengarang.setText(buyku.pengarang)
}
}

}