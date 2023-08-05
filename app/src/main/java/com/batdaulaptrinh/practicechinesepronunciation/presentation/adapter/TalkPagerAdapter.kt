package com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.batdaulaptrinh.practicechinesepronunciation.domain.model_ui.NewSpeechFlashCard
import com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.talk.TalkPagerFragment

class TalkPagerAdapter(
    private val speeches: List<NewSpeechFlashCard>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = speeches.size

    override fun createFragment(position: Int): Fragment {
        val data = speeches[position]
        val fragment = TalkPagerFragment({ _position, isChineseVisible ->
            speeches[_position].isChineseActivated = isChineseVisible
        }, { _position, isPinyinVisible ->
            speeches[_position].isPinyinActivated = isPinyinVisible
        })
        fragment.arguments = Bundle().apply {
            putParcelable("data", data)
        }
        return fragment
    }
}