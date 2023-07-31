package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.talk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
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
        } else if (navArgs.speech != null) {
            val talkPagerAdapter =
                TalkPagerAdapter(listOf(navArgs.speech!!), childFragmentManager, lifecycle)
            binding.vpSpeeches.adapter = talkPagerAdapter
        }
        talkViewModel.speeches.observe(viewLifecycleOwner) {
            val talkPagerAdapter = TalkPagerAdapter(it, childFragmentManager, lifecycle)
            binding.vpSpeeches.adapter = talkPagerAdapter
        }
        binding.btnMicro.setOnClickListener {
//            startListening()
        }
        binding.btnSpeaker.setOnClickListener {
//            tts.speak(speech.chinese, TextToSpeech.QUEUE_FLUSH, null, "")
        }
        binding.btnChinese.setOnClickListener {
            binding.btnChinese.alpha = 1.0f
//            binding.tvChinese.isVisible = true
        }
        binding.btnPinyin.setOnClickListener {
            binding.btnPinyin.alpha = 1.0f
//            binding.tvPinyin.isVisible = true
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}