package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.talk

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.batdaulaptrinh.practicechinesepronunciation.databinding.FragmentTalkBinding
import com.batdaulaptrinh.practicechinesepronunciation.domain.logic.TTS
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TalkFragment() : Fragment() {
    private var _binding: FragmentTalkBinding? = null
    private val navArgs: TalkFragmentArgs by navArgs()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTalkBinding.inflate(inflater, container, false)
        val lessonTitle = navArgs.speech.lessonTitle.split(" ").subList(0, 2).joinToString(": ")
        binding.tvLessonTitle.text = lessonTitle
        navArgs.speech.apply {
            binding.tvEnglish.text = english
            binding.tvChinese.text = chinese
            binding.tvPinyin.text = pinyin
        }
        binding.btnMicro.setOnClickListener {
            //TODO
            Toast.makeText(requireContext(), "OPEN STT", Toast.LENGTH_SHORT).show()
            startTalking()
        }
        binding.btnSpeaker.setOnClickListener {
            TTS(requireActivity(), navArgs.speech.chinese)
        }
        binding.btnChinese.setOnClickListener {
            binding.btnChinese.alpha = 1.0f
            binding.tvChinese.isVisible = true
        }
        binding.btnPinyin.setOnClickListener {
            binding.btnPinyin.alpha = 1.0f
            binding.tvPinyin.isVisible = true
        }
        binding.btnNext.setOnClickListener {
            //TODO
        }
        binding.btnPrevious.setOnClickListener {
            //TODO
        }
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startTalking() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.activity?.packageName)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1000)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Your Prompt")
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            "zh-CN"
        )
        requireActivity().startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
    }
}

private const val REQUEST_CODE_SPEECH_INPUT = 1082
