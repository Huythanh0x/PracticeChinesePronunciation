package com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.batdaulaptrinh.practicechinesepronunciation.databinding.WeekItemBinding
import com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter.diffutils.DiffUtilStringCallback

class WeekRecyclerViewAdapter(
    private val weekTitles: MutableList<String>,
    private val itemCLickListener: (weekTitle: String) -> Unit
) :
    RecyclerView.Adapter<WeekRecyclerViewAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: WeekItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weekTitle: String, itemCLickListener: (weekTitle: String) -> Unit) {
            binding.tvWeekTitle.text = weekTitle
            binding.root.setOnClickListener {
                itemCLickListener(weekTitle)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = WeekItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(weekTitles[position], itemCLickListener)
    }

    override fun getItemCount() = weekTitles.size

    fun setList(newWeekTitles: List<String>) {
        val diffCallback = DiffUtilStringCallback(weekTitles, newWeekTitles)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        weekTitles.clear()
        weekTitles.addAll(newWeekTitles)
        diffResult.dispatchUpdatesTo(this)

    }
}
