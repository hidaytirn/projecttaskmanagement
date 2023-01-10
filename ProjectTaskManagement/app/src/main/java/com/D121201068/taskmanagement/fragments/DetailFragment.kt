package com.D121201068.taskmanagement.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.D121201068.taskmanagement.R
import com.D121201068.taskmanagement.databinding.FragmentDetailBinding
import com.D121201068.taskmanagement.databinding.FragmentEditBinding
import com.D121201068.taskmanagement.model.Tugas
import com.D121201068.taskmanagement.viewmodel.TugasViewModel


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding?= null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel : TugasViewModel

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]

        binding.judulTugas.text = args.currentTugas.judul_tugas
        binding.kategoriTugas.text = args.currentTugas.kategori
        binding.deskripsiTugas.text = args.currentTugas.isi_tugas

        binding.btnKembali.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_berandaFragment)
        }
        binding.btnSelesai.setOnClickListener {
            val update = Tugas(args.currentTugas.id,args.currentTugas.kategori,args.currentTugas.judul_tugas,args.currentTugas.isi_tugas,"Selesai")
            tugasViewModel.updateTugas(update)
            findNavController().navigate(R.id.action_detailFragment_to_berandaFragment)
        }


        return view
    }
}