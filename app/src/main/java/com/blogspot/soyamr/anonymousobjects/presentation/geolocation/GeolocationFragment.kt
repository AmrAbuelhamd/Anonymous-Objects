package com.blogspot.soyamr.anonymousobjects.presentation.geolocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI.setupWithNavController
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
        viewModel.error.observe(viewLifecycleOwner, ::showError)
    }

    private fun showCurrentObjectData(currentObject: Object?) {
        currentObject?.let {
            with(binding) {
                objectNameTextView.text = getString(R.string.set_name, it.name.toString())
                objectTitleTextView.text = getString(R.string.set_title, it.title.toString())
                statusTextView.text = getString(R.string.set_status, it.tags.toString())
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
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15F))
        }
    }

    private val args: GeolocationFragmentArgs by navArgs()

    override fun onStart() {
        super.onStart()
        binding.toolbar.title = getString(R.string.object_data)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = get()
        return GeolocationFragmentBinding.inflate(inflater, container, false).run {
            _binding = this

           setViews()

            root
        }
    }

    private fun setViews() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        viewModel.setCurrentObject(args.objectId)
        setupWithNavController(binding.toolbar, findNavController())
    }

    private fun showError(error: String?) {
        error?.let {
            if (it.isNotBlank())
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

}