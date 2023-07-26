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
import com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter.WeekRecyclerViewAdapter

class WeekFragment : Fragment() {
    private var _binding: FragmentWeekBinding? = null
    private val binding get() = _binding!!
    private val navArgs: WeekFragmentArgs by navArgs()
    lateinit var recyclerViewAdapter: WeekRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeekBinding.inflate(inflater, container, false)
        recyclerViewAdapter = WeekRecyclerViewAdapter(mutableListOf()) {
            val action =
                WeekFragmentDirections.actionWeekFragmentToLessonFragment(it)
            binding.root.findNavController().navigate(action)
        }
        binding.rvWeeks.adapter = recyclerViewAdapter
        Toast.makeText(requireContext(), navArgs.weekTitle, Toast.LENGTH_LONG).show()
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}