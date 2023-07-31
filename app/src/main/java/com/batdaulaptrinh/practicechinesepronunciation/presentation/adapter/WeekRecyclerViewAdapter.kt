package com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.core.view.isVisible
import com.batdaulaptrinh.practicechinesepronunciation.data.model.LessonData
import com.batdaulaptrinh.practicechinesepronunciation.databinding.LessonItemBinding
import com.batdaulaptrinh.practicechinesepronunciation.databinding.WeekItemBinding

class WeekRecyclerViewAdapter(
    private val context: Context,
    private val weeks: LinkedHashMap<String, List<LessonData>>,
    private val lessonClickListener: (lesson: LessonData) -> Unit
) :
    BaseExpandableListAdapter() {
    override fun getGroupCount() = weeks.size

    override fun getChildrenCount(position: Int) = weeks.values.toList()[position].size

    override fun getGroup(position: Int): String =
        weeks.keys.toList()[position]

    override fun getChild(parentPos: Int, childPos: Int): LessonData =
        weeks.values.toList()[parentPos][childPos]

    override
    fun getGroupId(position: Int): Long =
        weeks.keys.toList()[position].hashCode().toLong()

    override fun getChildId(parentPos: Int, childPos: Int) =
        weeks.values.toList()[parentPos].hashCode().toLong()

    override fun hasStableIds() = false

    override fun getGroupView(
        position: Int,
        isLastItem: Boolean,
        convertView: View?,
        parentView: ViewGroup?
    ): View {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = WeekItemBinding.inflate(layoutInflater)
        binding.tvWeekTitle.text = getGroup(position)
        return binding.root
    }

    override fun getChildView(
        parentPos: Int,
        childPos: Int,
        isLastChild: Boolean,
        convertView: View?,
        parentView: ViewGroup?
    ): View {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = LessonItemBinding.inflate(layoutInflater)
        val currentLesson = getChild(parentPos, childPos)
        binding.tvLessonTitle.text = currentLesson.lessonTitle
        binding.tvSpeeches.text = currentLesson.speeches.joinToString(" ")
        binding.ivCompletedLesson.isVisible = currentLesson.isLessonCompleted
        binding.root.setOnClickListener {
            lessonClickListener(currentLesson)
        }
        return binding.root
    }

    override fun isChildSelectable(parentPos: Int, childPos: Int) = true
}
