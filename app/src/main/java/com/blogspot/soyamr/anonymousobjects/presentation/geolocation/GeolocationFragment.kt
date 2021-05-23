package com.blogspot.soyamr.anonymousobjects.presentation.geolocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.blogspot.soyamr.anonymousobjects.R
import com.blogspot.soyamr.anonymousobjects.databinding.GeolocationFragmentBinding
import com.blogspot.soyamr.domain.models.Object
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.get

class GeolocationFragment : Fragment() {

    private lateinit var viewModel: GeolocationViewModel
    private lateinit var googleMap: GoogleMap

    private var _binding: GeolocationFragmentBinding? = null
    private val binding get() = _binding!!

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        setViewModelListeners()
    }

    private fun setViewModelListeners() {
        viewModel.location.observe(viewLifecycleOwner, ::onGettingLocation)
        viewModel.loading.observe(viewLifecycleOwner, ::changeLoadingState)
        viewModel.currentObject.observe(viewLifecycleOwner, ::showCurrentObjectData)
    }

    private fun showCurrentObjectData(currentObject: Object?) {
        currentObject?.let {
            with(binding) {
                objectNameTextView.text = it.name
                objectTitleTextView.text = it.title
                statusTextView.text = it.tags.toString()
            }
        }
    }

    private fun changeLoadingState(loading: Boolean?) {
        loading?.let {
            binding.progressCircular.isVisible = loading
        }
    }

    private fun onGettingLocation(latLng: LatLng?) {
        latLng?.let {
            changeLoadingState(false)
            googleMap.addMarker(MarkerOptions().position(latLng))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15F))
        }
    }

    val args: GeolocationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        viewModel.setCurrentObject(args.objectId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = get()
        return GeolocationFragmentBinding.inflate(inflater, container, false).run {
            _binding = this
            root
        }
    }
}