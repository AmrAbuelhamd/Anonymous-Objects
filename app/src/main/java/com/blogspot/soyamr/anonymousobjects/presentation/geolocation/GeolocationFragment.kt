package com.blogspot.soyamr.anonymousobjects.presentation.geolocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blogspot.soyamr.anonymousobjects.databinding.GeolocationFragmentBinding
import org.koin.android.ext.android.get

class GeolocationFragment : Fragment() {

    private lateinit var viewModel: GeolocationViewModel

    private var _binding: GeolocationFragmentBinding? = null
    private val binding get() = _binding!!

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