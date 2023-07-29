package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.speech

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.batdaulaptrinh.practicechinesepronunciation.databinding.FragmentSpeechBinding
import com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter.SpeechRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpeechFragment : Fragment() {
    private var _binding: FragmentSpeechBinding? = null
    private val binding get() = _binding!!
    private val navArgs: SpeechFragmentArgs by navArgs()
    private val speechViewModel: SpeechViewModel by viewModels()
    lateinit var recyclerViewAdapter: SpeechRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeechBinding.inflate(inflater, container, false)
        recyclerViewAdapter = SpeechRecyclerViewAdapter(mutableListOf()) {
            Toast.makeText(requireContext(), it.chinese, Toast.LENGTH_LONG).show()
        }
        binding.rvSpeeches.adapter = recyclerViewAdapter
        speechViewModel.speeches.observe(viewLifecycleOwner) {
            recyclerViewAdapter.setList(it)
        }
        speechViewModel.loadSpeeches(navArgs.lessonTitle)
        val lessonTitle = navArgs.lessonTitle.split(" ").subList(0,2).joinToString(": ")
        binding.tvLessonTitle.text = lessonTitle
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}