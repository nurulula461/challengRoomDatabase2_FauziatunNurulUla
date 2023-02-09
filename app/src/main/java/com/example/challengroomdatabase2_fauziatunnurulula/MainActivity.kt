package com.example.challengroomdatabase2_fauziatunnurulula


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengroomdatabase2_fauziatunnurulula.Room.constant
import com.example.challengroomdatabase2_fauziatunnurulula.Room.dbPerpus
import com.example.challengroomdatabase2_fauziatunnurulula.Room.tb_buku
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { dbPerpus(this) }
    lateinit var PerpusAdapter : perpusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pindahperpus()
        setupRecyclerView()
        pindahPinjam()

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }
    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val perpus = db.tbBukuDao().dataBuku()
            Log.d("MainAcivity","dbResponce : $perpus")
            withContext(Dispatchers.Main){
                PerpusAdapter.setData(perpus)
            }

        }
    }
    fun pindahperpus(){
        btn_rvPerpus.setOnClickListener{
           intentEdit(0,constant.TYPE_CREATE)
        }
    }
    fun pindahPinjam(){
        input_Pinjam.setOnClickListener {
            startActivity(Intent(this,peminjam_Activity::class.java))
        }
    }

     fun  intentEdit(tbBukuId: Int, intentType: Int ){
         startActivity(
             Intent(applicationContext, edit_perpus::class.java)
                 .putExtra("intent_id", tbBukuId)
                 .putExtra("intent_Type", intentType)
         )
     }
     private fun setupRecyclerView(){
        PerpusAdapter = perpusAdapter(arrayListOf(), object: perpusAdapter.onAdapterListener{
            override fun onClick(tbBuku: tb_buku) {
                intentEdit(tbBuku.id_buku,constant.TYPE_READ)

            }
            override fun onUpdate(tbBuku: tb_buku) {
                intentEdit(tbBuku.id_buku,constant.TYPE_UPDATE)
            }

            override fun onDelete(tbBuku: tb_buku) {
                deleteDialog(tbBuku)
            }

        })
        Rv_perpus.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = PerpusAdapter
        }

    }
    private fun deleteDialog(tbBuku: tb_buku){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin hapus ${tbBuku.judul}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbBukuDao().deleteBuku(tbBuku)
                    loadData()
                }
            }
        }
        alertDialog.show()
    }
}