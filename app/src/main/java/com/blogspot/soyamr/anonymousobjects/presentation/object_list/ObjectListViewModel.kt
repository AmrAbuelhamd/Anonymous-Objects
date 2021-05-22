package com.blogspot.soyamr.anonymousobjects.presentation.object_list

import androidx.lifecycle.*
import com.blogspot.soyamr.domain.models.Object
import com.blogspot.soyamr.domain.usecases.GetObjectsList
import com.blogspot.soyamr.domain.usecases.UpdateCacheFromServer
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ObjectListViewModel(
    private val getObjectsList: GetObjectsList,
    private val updateCacheFromServer: UpdateCacheFromServer
) : ViewModel() {

    fun getFreshData() {
        viewModelScope.launch { updateCacheFromServer() }
    }

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    val objectList: LiveData<List<Object>> =
        getObjectsList()
            .onStart { _loading.postValue(true) }
            .asLiveData()
}