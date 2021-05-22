package com.blogspot.soyamr.anonymousobjects.presentation.object_list.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.domain.models.Object

class ObjectListAdapter(private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<ObjectListViewHolder>() {

    var objects: List<Object> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectListViewHolder {
        return ObjectListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ObjectListViewHolder, position: Int) {
        holder.bind(objects[position], listener)
    }

    override fun getItemCount(): Int = objects.count()

}