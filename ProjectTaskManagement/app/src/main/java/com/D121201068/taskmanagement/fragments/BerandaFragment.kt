package com.D121201068.taskmanagement.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201068.taskmanagement.R
import com.D121201068.taskmanagement.adapter.TugasAdapter
import com.D121201068.taskmanagement.databinding.FragmentBerandaBinding
import com.D121201068.taskmanagement.viewmodel.TugasViewModel

class BerandaFragment : Fragment() {
    private var _binding : FragmentBerandaBinding ?= null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel : TugasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentBerandaBinding.inflate(inflater, container, false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        val adapter_tugas = TugasAdapter()
        binding.recyletugas.adapter = adapter_tugas
        binding.recyletugas.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        tugasViewModel.readAllData.observe(viewLifecycleOwner){task->
            adapter_tugas.setData(task)
            if (task.size==0){
                binding.nodata.visibility = View.VISIBLE
                binding.recyletugas.visibility = View.GONE
            }else{
                binding.nodata.visibility = View.GONE
                binding.recyletugas.visibility = View.VISIBLE
            }
        }

        return view
    }

}