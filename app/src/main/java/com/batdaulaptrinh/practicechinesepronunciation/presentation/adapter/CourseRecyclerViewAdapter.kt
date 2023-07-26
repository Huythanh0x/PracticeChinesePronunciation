package com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.batdaulaptrinh.practicechinesepronunciation.databinding.CourseItemBinding
import com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter.diffutils.DiffUtilStringCallback

class CourseRecyclerViewAdapter(
    private val courseTitles: MutableList<String>,
    private val itemCLickListener: (courseTitle: String) -> Unit
) :
    RecyclerView.Adapter<CourseRecyclerViewAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: CourseItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(courseTitle: String, itemCLickListener: (courseTitle: String) -> Unit) {
            binding.tvCourseTitle.text = courseTitle
            binding.root.setOnClickListener {
                itemCLickListener(courseTitle)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(courseTitles[position], itemCLickListener)
    }

    override fun getItemCount() = courseTitles.size

    fun setList(newCourseTitles: List<String>) {
        val diffCallback = DiffUtilStringCallback(courseTitles, newCourseTitles)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        courseTitles.clear()
        courseTitles.addAll(newCourseTitles)
        diffResult.dispatchUpdatesTo(this)
    }
}