package com.blogspot.soyamr.anonymousobjects.presentation.object_list.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.anonymousobjects.R
import com.blogspot.soyamr.anonymousobjects.databinding.ObjectItemBinding
import com.blogspot.soyamr.domain.models.Object

class ObjectListViewHolder private constructor(private val binding: ObjectItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Object, listener: (Int) -> Unit) {
        with(binding) {
            titleTextView.text = item.title
            nameTextView.text = item.name
            if (!item.tags.isNullOrEmpty()) {
                binding.statusHolder.removeAllViews()
                for(i in item.tags!!.indices){
                    View.inflate(binding.root.context, R.layout.cat_view, binding.statusHolder)
                }
            }else{
                binding.statusHolder.removeAllViews()
            }
            root.setOnClickListener { listener(item.id) }
        }
    }

    companion object {
        fun from(parent: ViewGroup): ObjectListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ObjectItemBinding.inflate(layoutInflater, parent, false)
            return ObjectListViewHolder(binding)
        }
    }
}