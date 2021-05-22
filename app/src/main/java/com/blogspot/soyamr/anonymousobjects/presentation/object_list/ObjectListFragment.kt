package com.blogspot.soyamr.anonymousobjects.presentation.object_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blogspot.soyamr.anonymousobjects.databinding.ObjectListFragmentBinding
import com.blogspot.soyamr.domain.ObjectDatasource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class ObjectListFragment : Fragment() {

    private lateinit var viewModel: ObjectListViewModel

    private var _binding: ObjectListFragmentBinding? = null
    private val binding get() = _binding!!

    val repo: ObjectDatasource by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            repo.getObjectsList().collect {
                println(it.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = get()
        return ObjectListFragmentBinding.inflate(inflater, container, false).run {
            _binding = this
            root
        }
    }

}