package com.D121201068.taskmanagement.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.D121201068.taskmanagement.R
import com.D121201068.taskmanagement.fragments.BerandaFragmentDirections
import com.D121201068.taskmanagement.model.Tugas
import com.D121201068.taskmanagement.viewmodel.TugasViewModel

class TugasAdapter:RecyclerView.Adapter<TugasAdapter.MyViewHolder>() {
    private var list_tugas= emptyList<Tugas>()
    private var context:Context?=null
    private lateinit var tugasViewModel: TugasViewModel
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val judul:TextView = itemView.findViewById(R.id.judul)
        val deskripsi:TextView = itemView.findViewById(R.id.isi)
        val opsi:ImageView = itemView.findViewById(R.id.opsi)
        val warna:FrameLayout = itemView.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        tugasViewModel = ViewModelProvider(context as FragmentActivity)[TugasViewModel::class.java]
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list_tugas[position]
        holder.judul.text = currentItem.judul_tugas
        holder.deskripsi.text = currentItem.isi_tugas

        holder.opsi.setOnClickListener {
            val popupMenu = PopupMenu(context,it)
            popupMenu.inflate(R.menu.menu_opsi)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.edit_tugas->{
                        holder.itemView.findNavController().navigate(BerandaFragmentDirections.actionBerandaFragmentToEditFragment(list_tugas[position]))
                        true
                    }
                    R.id.hapus_tugas->{
                        tugasViewModel.deleteTugas(list_tugas[position])
                        true
                    }else->true
                }
            }
            popupMenu.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenu)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java).invoke(menu,true)
        }

        when(currentItem.kategori){
            "Penting Mendesak"->{
                holder.warna.backgroundTintList=ColorStateList.valueOf(context!!.resources.getColor(R.color.ungu))
            }
            "Tidak Penting Mendesak"->{
                holder.warna.backgroundTintList=ColorStateList.valueOf(context!!.resources.getColor(R.color.hijau))
            }
            "Penting Tidak Mendesak"->{
                holder.warna.backgroundTintList=ColorStateList.valueOf(context!!.resources.getColor(R.color.pink))
            }
            "Tidak Penting Tidak Mendesak"->{
                holder.warna.backgroundTintList=ColorStateList.valueOf(context!!.resources.getColor(R.color.kuning))
            }
        }



        if (currentItem.status.equals("Selesai")){
            holder.opsi.visibility = View.GONE
        }else{
            holder.warna.setOnClickListener {
                holder.itemView.findNavController().navigate(BerandaFragmentDirections.actionBerandaFragmentToDetailFragment(list_tugas[position]))
            }
            holder.opsi.visibility = View.VISIBLE
        }



    }

    override fun getItemCount(): Int {
        return list_tugas.size
    }

    fun setData(tugas:List<Tugas>){
        this.list_tugas = tugas
        notifyDataSetChanged()
    }
}