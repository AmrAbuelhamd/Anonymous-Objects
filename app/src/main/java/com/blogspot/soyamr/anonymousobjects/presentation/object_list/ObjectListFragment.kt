package com.blogspot.soyamr.anonymousobjects.presentation.object_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.anonymousobjects.databinding.ObjectListFragmentBinding
import com.blogspot.soyamr.anonymousobjects.presentation.object_list.recycler.ObjectListAdapter
import com.blogspot.soyamr.domain.models.Object
import org.koin.android.ext.android.get

class ObjectListFragment : Fragment() {

    private lateinit var viewModel: ObjectListViewModel

    private var _binding: ObjectListFragmentBinding? = null
    private val binding get() = _binding!!

    private val objectsAdapter: ObjectListAdapter by lazy {
        ObjectListAdapter() {
            findNavController().navigate(
                ObjectListFragmentDirections.actionObjectListFragmentToGeolocationFragment(
                    it
                )
            )
        }
    }

    private fun setViewModelListeners() {
        viewModel.loading.observe(viewLifecycleOwner, ::changeLoadingState)
        viewModel.objectList.observe(viewLifecycleOwner, ::updateObjetList)
        viewModel.error.observe(viewLifecycleOwner, ::showError)
    }

    private fun showError(error: String?) {
        error?.let {
            if (it.isNotBlank())
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateObjetList(list: List<Object>?) {
        list?.let {
            objectsAdapter.objects = it
            changeLoadingState(false)
        }
    }

    private fun changeLoadingState(isLoading: Boolean?) {
        isLoading?.let {
            binding.swipeRefreshLayout.isRefreshing = isLoading
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = get()

        return ObjectListFragmentBinding.inflate(inflater, container, false).run {
            _binding = this
            recyclerView.adapter = objectsAdapter
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getFreshData()
            }
            setViewModelListeners()
            root
        }
    }

}