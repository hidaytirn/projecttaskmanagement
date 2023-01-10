package com.D121201068.taskmanagement.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tabeltugas")
data class Tugas(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val kategori : String,
    val judul_tugas : String,
    val isi_tugas : String,
    val status : String
):Parcelable
