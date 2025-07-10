package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(): ViewModel(){

val dao = MainApp.db.quotesDao() //dao

    val data: LiveData<List<Data>> = dao.getAllQuotes() //


    fun addQuotess(qoutes: String,item: String){
        viewModelScope.launch(Dispatchers.IO) {
        dao.addQuotes(Data(quotes = qoutes, item = item))
        }
    }

    fun delete(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteTodo(id)
        }

    }

}