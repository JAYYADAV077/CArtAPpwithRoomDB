package com.example.myapplication.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Data


@Dao
interface Dao {
    @Query("SELECT * FROM data ORDER BY id DESC")
    fun getAllQuotes(): LiveData<List<Data>>

    @Insert
    fun addQuotes(data: Data)

    @Query("DELETE FROM Data where id = :id")
    fun deleteTodo(id: Int)

}

