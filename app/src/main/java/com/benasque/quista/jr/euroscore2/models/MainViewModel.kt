package com.benasque.quista.jr.euroscore2.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel(){

    private val _resultado: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getresultado() = _resultado as LiveData<String>
    fun setresultado(i: String){
       _resultado?.value = i
    }


}