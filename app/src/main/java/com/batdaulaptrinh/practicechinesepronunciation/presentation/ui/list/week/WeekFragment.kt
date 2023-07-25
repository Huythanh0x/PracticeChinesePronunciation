package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.week

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.batdaulaptrinh.practicechinesepronunciation.databinding.FragmentWeekBinding

class WeekFragment : Fragment() {
    private var _binding: FragmentWeekBinding? = null
    private val binding get() = _binding!!
    private val navArgs: WeekFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeekBinding.inflate(inflater, container, false)
        binding.btnNext.setOnClickListener {
            val action =
                WeekFragmentDirections.actionWeekFragmentToLessonFragment("sample week title")
            binding.root.findNavController().navigate(action)
        }
        Toast.makeText(requireContext(), navArgs.weekTitle, Toast.LENGTH_LONG).show()
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}