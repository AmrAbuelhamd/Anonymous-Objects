package com.blogspot.soyamr.anonymousobjects.presentation.object_list

import androidx.lifecycle.*
import com.blogspot.soyamr.anonymousobjects.presentation.common.BaseViewModel
import com.blogspot.soyamr.domain.models.Object
import com.blogspot.soyamr.domain.usecases.GetObjectsList
import com.blogspot.soyamr.domain.usecases.UpdateCacheFromServer
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onErrorCollect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ObjectListViewModel(
    getObjectsList: GetObjectsList,
    private val updateCacheFromServer: UpdateCacheFromServer
) : BaseViewModel() {

    val objectList: LiveData<List<Object>> =
        getObjectsList()
            .onStart { _loading.postValue(true) }
            .map { list ->
                list.sortedWith(
                    compareBy(
                        { it.tags?.sum() ?: 1000000 },
                        { it.name.filter { ch -> ch.isDigit() }.toInt() },
                        { it.title }
                    )
                )
            }
            .asLiveData()

    fun getFreshData() {
        viewModelScope.launch(handler) { updateCacheFromServer() }
    }
}