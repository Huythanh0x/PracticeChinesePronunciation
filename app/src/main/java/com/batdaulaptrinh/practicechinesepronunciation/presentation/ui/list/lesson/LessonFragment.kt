package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.batdaulaptrinh.practicechinesepronunciation.databinding.FragmentLessonBinding
import com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter.LessonRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LessonFragment : Fragment() {
    private var _binding: FragmentLessonBinding? = null
    private val binding get() = _binding!!
    private val navArgs: LessonFragmentArgs by navArgs()
    private val lessonViewModel: LessonViewModel by viewModels()
    lateinit var recyclerViewAdapter: LessonRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        recyclerViewAdapter = LessonRecyclerViewAdapter(mutableListOf()) {
            val action = LessonFragmentDirections.actionLessonFragmentToSpeechFragment(it)
            binding.root.findNavController().navigate(action)
        }
        binding.rvLessons.adapter = recyclerViewAdapter
        lessonViewModel.lessons.observe(viewLifecycleOwner) {
            recyclerViewAdapter.setList(it)
        }
        lessonViewModel.loadLessons(navArgs.lessonTitle)
        binding.tvWeekTitle.text = navArgs.lessonTitle
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}