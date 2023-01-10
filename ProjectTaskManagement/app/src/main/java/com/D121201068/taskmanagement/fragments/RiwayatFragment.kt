package com.D121201068.taskmanagement.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201068.taskmanagement.R
import com.D121201068.taskmanagement.adapter.TugasAdapter
import com.D121201068.taskmanagement.databinding.FragmentBerandaBinding
import com.D121201068.taskmanagement.databinding.FragmentRiwayatBinding
import com.D121201068.taskmanagement.viewmodel.TugasViewModel


class RiwayatFragment : Fragment() {
    private var _binding : FragmentRiwayatBinding?= null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel : TugasViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentRiwayatBinding.inflate(inflater, container, false)
        val view = binding.root


        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        val adapter_tugas = TugasAdapter()
        binding.recyletugas.adapter = adapter_tugas
        binding.recyletugas.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)

        tugasViewModel.readAllDataRiwayat.observe(viewLifecycleOwner){task->
            adapter_tugas.setData(task)
            if (task.size==0){
                binding.nodata.visibility = View.VISIBLE
                binding.recyletugas.visibility = View.GONE
            }else{
                binding.nodata.visibility = View.GONE
                binding.recyletugas.visibility = View.VISIBLE
            }
        }

        binding.delete.setOnClickListener {
            val view = View.inflate(context,R.layout.dialog_button,null)
            val builder = AlertDialog.Builder(context)
            builder.setView(view)

            val dialog = builder.create()
            val yakin = view.findViewById<Button>(R.id.yakin)
            val batal = view.findViewById<Button>(R.id.batal)

            dialog.show()
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            yakin.setOnClickListener {
                tugasViewModel.deleteAllRiwayat()
                Toast.makeText(requireContext(),"Berhasil menghapus riwayat",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            batal.setOnClickListener {
                dialog.dismiss()
            }
        }


        return view
    }
}