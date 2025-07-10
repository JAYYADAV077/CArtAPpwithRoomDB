package com.example.myapplication



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val quotes: String,
    val item: String
) {}
