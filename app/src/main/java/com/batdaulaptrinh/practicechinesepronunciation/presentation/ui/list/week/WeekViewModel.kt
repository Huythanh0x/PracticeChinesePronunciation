package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.week

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist.GetDisplayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeekViewModel @Inject constructor(private val getDisplayListUseCase: GetDisplayListUseCase) :
    ViewModel() {
    val weekTitles = MutableLiveData<List<String>>()
    fun loadWeekTitles(courseTitle: String) {
        viewModelScope.launch {
            weekTitles.postValue(getDisplayListUseCase.getListWeekUseCase(courseTitle))
        }
    }
}