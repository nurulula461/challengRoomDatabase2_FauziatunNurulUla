package com.example.challengroomdatabase2_fauziatunnurulula

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengroomdatabase2_fauziatunnurulula.Room.tb_buku
import kotlinx.android.synthetic.main.activity_edit_perpus.view.*
import kotlinx.android.synthetic.main.activity_perpus_adapter.view.*

class perpusAdapter (private  val buku: ArrayList<tb_buku>, private val listener: onAdapterListener): RecyclerView.Adapter<perpusAdapter.BukuViewHolder>(){

    class BukuViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
      return BukuViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.activity_perpus_adapter,parent,false)
      )
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val buk = buku [position]
        holder.view.t_judul.text = buk.judul
        holder.view.t_kategori.text = buk.kategori
        holder.view.CVbuku.setOnClickListener{
            listener.onClick(buk)
        }
        holder.view.ic_edit.setOnClickListener{
            listener.onUpdate(buk)
        }
        holder.view.ic_delete.setOnClickListener{
            listener.onDelete(buk)
        }

    }

    override fun getItemCount() = buku.size


     fun setData(list: List<tb_buku>){
         buku.clear()
         buku.addAll(list)
         notifyDataSetChanged()
    }
    interface onAdapterListener{
        fun onClick(tbBuku: tb_buku)
        fun onUpdate(tbBuku: tb_buku)
        fun onDelete(tbBuku: tb_buku)
    }

}