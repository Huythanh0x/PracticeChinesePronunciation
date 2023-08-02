package com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity
import com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.talk.TalkPagerFragment

class TalkPagerAdapter(
    private val speeches: List<NewSpeechEntity>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = speeches.size

    override fun createFragment(position: Int): Fragment {
        val data = speeches[position]
        val fragment = TalkPagerFragment()
        fragment.arguments = Bundle().apply {
            putParcelable("data", data)
        }
        return fragment
    }
}