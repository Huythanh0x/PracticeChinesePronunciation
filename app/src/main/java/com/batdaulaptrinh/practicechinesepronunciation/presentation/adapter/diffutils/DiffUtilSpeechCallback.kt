package com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity

class DiffUtilSpeechCallback(
    private val oldList: List<NewSpeechEntity>,
    private val newList: List<NewSpeechEntity>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.lessonTitle == newItem.lessonTitle
    }
}