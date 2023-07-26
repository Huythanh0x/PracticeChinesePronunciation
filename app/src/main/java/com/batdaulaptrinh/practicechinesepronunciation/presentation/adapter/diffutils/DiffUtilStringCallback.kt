package com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import java.util.Objects

class DiffUtilStringCallback(private val oldList: List<String>, private val newList: List<String>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return Objects.equals(oldItem, newItem)
    }
}