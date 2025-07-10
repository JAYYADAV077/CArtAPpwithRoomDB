package com.example.myapplication.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.Data


@Database(entities = [Data::class], version = 2)
abstract class DB: RoomDatabase() {


    companion object{
        const val NAME = "DB"
    }

    abstract fun quotesDao():Dao

}