package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.batdaulaptrinh.practicechinesepronunciation.databinding.FragmentCourseBinding
import com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter.CourseRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseFragment : Fragment() {
    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!
    private val courseViewModel: CourseViewModel by viewModels()
    lateinit var recyclerViewAdapter: CourseRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseBinding.inflate(layoutInflater, container, false)
        recyclerViewAdapter = CourseRecyclerViewAdapter(mutableListOf()) {
            val action =
                CourseFragmentDirections.actionCourseFragmentToWeekFragment(it)
            binding.root.findNavController().navigate(action)
        }
        binding.rvCourses.adapter = recyclerViewAdapter
        courseViewModel.courseTitles.observe(viewLifecycleOwner) {
            recyclerViewAdapter.setList(it)
        }
        courseViewModel.downloadExecutionResult.observe(viewLifecycleOwner) {
            courseViewModel.loadCourseTitles()
        }
        courseViewModel.tryUpdateDatabase()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}