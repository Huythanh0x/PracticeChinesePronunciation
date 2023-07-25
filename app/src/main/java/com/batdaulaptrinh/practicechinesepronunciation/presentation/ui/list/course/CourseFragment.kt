package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.batdaulaptrinh.practicechinesepronunciation.databinding.FragmentCourseBinding

class CourseFragment : Fragment() {
    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseBinding.inflate(layoutInflater, container, false)
        binding.btnNext.setOnClickListener {
            val action =
                CourseFragmentDirections.actionCourseFragmentToWeekFragment("sample course title")
            binding.root.findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}