package com.blogspot.soyamr.anonymousobjects.presentation.geolocation

import androidx.lifecycle.*
import com.blogspot.soyamr.domain.models.Object
import com.blogspot.soyamr.domain.usecases.GetGeolocation
import com.blogspot.soyamr.domain.usecases.GetObjectById
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GeolocationViewModel(
    val getGeolocation: GetGeolocation,
    val getObjectById: GetObjectById
) : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    val location: LiveData<LatLng> = liveData {
        _loading.value = true
        val location = getGeolocation()
        emit(LatLng(location.lat, location.lng))
    }

    private val _currentObject: MutableLiveData<Object> = MutableLiveData()
    val currentObject: LiveData<Object> = _currentObject

    fun setCurrentObject(id: Int) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            _currentObject.postValue(getObjectById(id))
        }
    }
}