package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.talk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.batdaulaptrinh.practicechinesepronunciation.R
import com.batdaulaptrinh.practicechinesepronunciation.data.mapper.Mapper.toFlashCardData
import com.batdaulaptrinh.practicechinesepronunciation.databinding.FragmentTalkBinding
import com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter.TalkPagerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TalkFragment() : Fragment() {
    private var _binding: FragmentTalkBinding? = null
    private val navArgs: TalkFragmentArgs by navArgs()
    private val talkViewModel: TalkViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTalkBinding.inflate(inflater, container, false)
        if (navArgs.lessonTile != null) {
            talkViewModel.loadSpeeches(navArgs.lessonTile!!)
            binding.tvLessonTitle.text =
                navArgs.lessonTile!!.split(" ").subList(0, 2).joinToString(" ")
        } else if (navArgs.speech != null) {
            val talkPagerAdapter =
                TalkPagerAdapter(
                    listOf(navArgs.speech!!.toFlashCardData()),
                    childFragmentManager,
                    lifecycle
                )
            binding.tvLessonTitle.text = resources.getString(R.string.single_phrase_title)
            binding.vpSpeeches.adapter = talkPagerAdapter
        }
        talkViewModel.speeches.observe(viewLifecycleOwner) {
            val talkPagerAdapter = TalkPagerAdapter(
                it.map { item -> item.toFlashCardData() },
                childFragmentManager,
                lifecycle
            )
            binding.vpSpeeches.adapter = talkPagerAdapter
        }
        binding.vpSpeeches.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.apply {
                    btnChinese.alpha =
                        if (getCurrentShowingTalkPagerFragment().isChineseActivated()) 1.0f else 0.5f
                    btnPinyin.alpha =
                        if (getCurrentShowingTalkPagerFragment().isPinyinActivated()) 1.0f else 0.5f
                    getCurrentShowingTalkPagerFragment().updateTheView()
                }
            }
        })
        binding.btnMicro.setOnClickListener {
            getCurrentShowingTalkPagerFragment().startListening()
        }
        binding.btnSpeaker.setOnClickListener {
            getCurrentShowingTalkPagerFragment().speakOut()
        }
        binding.btnChinese.setOnClickListener {
            if (binding.btnChinese.alpha == 0.5f) {
                binding.btnChinese.alpha = 1.0f
                getCurrentShowingTalkPagerFragment().setChineseVisibility(true)
            } else {
                binding.btnChinese.alpha = 0.5f
                getCurrentShowingTalkPagerFragment().setChineseVisibility(false)
            }
        }
        binding.btnPinyin.setOnClickListener {
            if (binding.btnPinyin.alpha == 0.5f) {
                binding.btnPinyin.alpha = 1.0f
                getCurrentShowingTalkPagerFragment().setPinyinVisibility(true)
            } else {
                binding.btnPinyin.alpha = 0.5f
                getCurrentShowingTalkPagerFragment().setPinyinVisibility(false)
            }
        }
        return binding.root
    }

    private fun getCurrentShowingTalkPagerFragment(): TalkPagerFragment {
        val myFragment =
            childFragmentManager.findFragmentByTag("f" + binding.vpSpeeches.currentItem)
        return (myFragment as TalkPagerFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}