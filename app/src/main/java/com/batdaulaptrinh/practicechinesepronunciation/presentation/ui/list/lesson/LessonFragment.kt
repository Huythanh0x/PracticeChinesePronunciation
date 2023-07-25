package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.batdaulaptrinh.practicechinesepronunciation.databinding.FragmentLessonBinding

class LessonFragment : Fragment() {
    private var _binding: FragmentLessonBinding? = null
    private val binding get() = _binding!!
    private val navArgs: LessonFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        binding.btnNext.setOnClickListener {
            val action = LessonFragmentDirections.actionLessonFragmentToSpeechFragment("sample lesson title")
            binding.root.findNavController().navigate(action)
        }
        Toast.makeText(requireContext(), navArgs.lessonTitle, Toast.LENGTH_LONG).show()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}