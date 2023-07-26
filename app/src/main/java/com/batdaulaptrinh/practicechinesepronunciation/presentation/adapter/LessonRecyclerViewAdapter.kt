package com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.batdaulaptrinh.practicechinesepronunciation.data.model.LessonData
import com.batdaulaptrinh.practicechinesepronunciation.databinding.LessonItemBinding
import com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter.diffutils.DiffUtilLessonCallback

class LessonRecyclerViewAdapter(
    private val lessons: MutableList<LessonData>,
    private val itemCLickListener: (weekTitle: String) -> Unit
) :
    RecyclerView.Adapter<LessonRecyclerViewAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: LessonItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lessonData: LessonData, itemCLickListener: (lessonTitle: String) -> Unit) {
            binding.ivCompletedLesson.isVisible = lessonData.isLessonCompleted
            binding.tvLessonTitle.text = lessonData.lessonTitle
            binding.tvSpeeches.text = lessonData.speeches.joinToString(", ")
            binding.root.setOnClickListener {
                itemCLickListener(lessonData.lessonTitle)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = LessonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(lessons[position], itemCLickListener)
    }

    override fun getItemCount() = lessons.size


    fun setList(newLessons: List<LessonData>) {
        val diffCallback = DiffUtilLessonCallback(lessons, newLessons)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        lessons.clear()
        lessons.addAll(newLessons)
        diffResult.dispatchUpdatesTo(this)
    }
}