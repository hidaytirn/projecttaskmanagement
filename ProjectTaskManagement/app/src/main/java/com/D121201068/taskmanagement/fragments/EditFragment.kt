package com.D121201068.taskmanagement.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201068.taskmanagement.R
import com.D121201068.taskmanagement.adapter.TugasAdapter
import com.D121201068.taskmanagement.databinding.FragmentBerandaBinding
import com.D121201068.taskmanagement.databinding.FragmentEditBinding
import com.D121201068.taskmanagement.model.Tugas
import com.D121201068.taskmanagement.viewmodel.TugasViewModel
import kotlinx.coroutines.launch


class EditFragment : Fragment() {
    private var _binding : FragmentEditBinding?= null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel : TugasViewModel

    private val args by navArgs<EditFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentEditBinding.inflate(inflater, container, false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]

        binding.judulTugas.setText(args.currentTugas.judul_tugas)
        binding.deskripsiTugas.setText(args.currentTugas.isi_tugas)
        binding.dropdownMenu.setText(args.currentTugas.kategori)
        binding.dropdownMenu.setAdapter(ArrayAdapter.createFromResource(requireContext(),R.array.kategoriArray,android.R.layout.simple_list_item_1))

        binding.btnKembali.setOnClickListener {
            findNavController().navigate(R.id.action_editFragment_to_berandaFragment)
        }
        binding.btnSimpan.setOnClickListener {
            val judul = binding.judulTugas.text.toString()
            val deskripsi = binding.deskripsiTugas.text.toString()
            val kategori = binding.dropdownMenu.text.toString()

            if(kategori.isEmpty()){
                Toast.makeText(requireContext(),"Pilih kategori terlebih dahulu", Toast.LENGTH_SHORT).show()
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

            val update = Tugas(args.currentTugas.id,kategori,judul,deskripsi,"Belum")
            tugasViewModel.updateTugas(update)
            findNavController().navigate(R.id.action_editFragment_to_berandaFragment)
        }

        return view
    }
}