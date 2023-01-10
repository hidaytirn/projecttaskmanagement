package com.D121201068.taskmanagement.repository

import androidx.lifecycle.LiveData
import com.D121201068.taskmanagement.data.TugasDao
import com.D121201068.taskmanagement.model.Tugas

class TugasRepository (private val tugasDao: TugasDao){
    val readAllData:LiveData<List<Tugas>> = tugasDao.readAllData()
    val readAllDataRiwayat:LiveData<List<Tugas>> = tugasDao.readAllDataRiwayat()
    suspend fun addTugas(tugas:Tugas){
        tugasDao.addTugas(tugas)
    }
    suspend fun updateTugas(tugas: Tugas){
        tugasDao.updateTugas(tugas)
    }
    suspend fun deleteTugas(tugas: Tugas){
        tugasDao.deleteTugas(tugas)
    }
    suspend fun deleteAllRiwayat(){
        tugasDao.deleteAllRiwayat()
    }
}