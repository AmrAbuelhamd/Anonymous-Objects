package com.blogspot.soyamr.anonymousobjects.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel : ViewModel() {

    protected val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData("")
    val error: LiveData<String> = _error

    val handler = CoroutineExceptionHandler { _, exception ->
        _loading.value = false
        _error.value = exception.message
    }
}