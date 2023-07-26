package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.speech

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.batdaulaptrinh.practicechinesepronunciation.databinding.FragmentSpeechBinding

class SpeechFragment : Fragment() {
    private var _binding: FragmentSpeechBinding? = null
    private val binding get() = _binding!!
    private val navArgs: SpeechFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeechBinding.inflate(inflater, container, false)
        Toast.makeText(requireContext(), navArgs.lessonTitle, Toast.LENGTH_LONG).show()
        val lessonTitle = navArgs.lessonTitle.split(" ").subList(0,2).joinToString(": ")
        binding.tvLessonTitle.text = lessonTitle
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}