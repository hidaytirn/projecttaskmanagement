package com.D121201068.taskmanagement.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.D121201068.taskmanagement.model.Tugas

@Dao
interface TugasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTugas(tugas: Tugas)

    @Query("SELECT * FROM tabeltugas WHERE status='Belum'")
    fun readAllData():LiveData<List<Tugas>>

    @Query("SELECT * FROM tabeltugas WHERE status='Selesai'")
    fun readAllDataRiwayat():LiveData<List<Tugas>>

    @Update
    suspend fun updateTugas(tugas: Tugas)

    @Delete
    suspend fun deleteTugas(tugas: Tugas)

    @Query("DELETE FROM tabeltugas WHERE status='Selesai'")
    suspend fun deleteAllRiwayat()
}