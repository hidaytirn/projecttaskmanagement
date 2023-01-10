package com.D121201068.taskmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.D121201068.taskmanagement.databinding.ActivityAddTaskBinding
import com.D121201068.taskmanagement.model.Tugas
import com.D121201068.taskmanagement.viewmodel.TugasViewModel
import kotlinx.coroutines.launch

class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding :ActivityAddTaskBinding
    private lateinit var kat_tugas:ArrayAdapter<CharSequence>
    private lateinit var tugasViewModel : TugasViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        kat_tugas = ArrayAdapter.createFromResource(this,R.array.kategoriArray,android.R.layout.simple_list_item_1)
        binding.dropdownMenu.setAdapter(kat_tugas)


        binding.btnKembali.setOnClickListener {
            finish()
        }

        binding.btnTambah.setOnClickListener {
            val judul = binding.judulTugas.text.toString()
            val deskripsi = binding.deskripsiTugas.text.toString()
            val kategori = binding.dropdownMenu.text.toString()

            if(kategori.isEmpty()){
                Toast.makeText(this,"Pilih kategori terlebih dahulu",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (judul.isEmpty()){
                binding.judulTugas.error = "Judul tidak boleh kosong"
                binding.judulTugas.requestFocus()
                return@setOnClickListener
            }
            if (deskripsi.isEmpty()){
                binding.deskripsiTugas.error = "Deskripsi tidak boleh kosong"
                binding.deskripsiTugas.requestFocus()
                return@setOnClickListener
            }

            lifecycleScope.launch{
                val tugas = Tugas(0,kategori,judul,deskripsi,"Belum")
                tugasViewModel.addTugas(tugas)
                Toast.makeText(this@AddTaskActivity,"Berhasil menambahkan tugas",Toast.LENGTH_SHORT).show()
                finish()
            }


        }


    }
}