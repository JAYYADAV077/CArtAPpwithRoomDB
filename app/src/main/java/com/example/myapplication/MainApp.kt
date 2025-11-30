package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.DB.DB

class MainApp: Application() {

    companion object{
        lateinit var db: DB
    }

    override fun onCreate() {
        super.onCreate()
      db = Room.databaseBuilder(
            applicationContext,
            DB::class.java,
            DB.NAME
        ).build()
    }
}


