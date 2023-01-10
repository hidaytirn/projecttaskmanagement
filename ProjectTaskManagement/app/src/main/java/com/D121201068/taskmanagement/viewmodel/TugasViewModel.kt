package com.D121201068.taskmanagement.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.D121201068.taskmanagement.data.TugasDatabase
import com.D121201068.taskmanagement.model.Tugas
import com.D121201068.taskmanagement.repository.TugasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TugasViewModel (application: Application):AndroidViewModel(application){
    val readAllData:LiveData<List<Tugas>>
    val readAllDataRiwayat:LiveData<List<Tugas>>
    private val repository:TugasRepository

    init {
        val tugasDao = TugasDatabase.getDatabase(application).tugasDao()
        repository = TugasRepository(tugasDao)
        readAllData = repository.readAllData
        readAllDataRiwayat = repository.readAllDataRiwayat
    }

    fun addTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTugas(tugas)
        }
    }
    fun updateTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTugas(tugas)
        }
    }
    fun deleteTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTugas(tugas)
        }
    }
    fun deleteAllRiwayat(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllRiwayat()
        }
    }

}