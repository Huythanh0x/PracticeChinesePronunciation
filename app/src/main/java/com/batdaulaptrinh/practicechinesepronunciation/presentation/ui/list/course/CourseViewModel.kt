package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.course

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist.GetDisplayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(private val getDisplayListUseCase: GetDisplayListUseCase) :
    ViewModel() {
    val courseTitles = MutableLiveData<List<String>>()
    fun loadCourseTitles() {
        viewModelScope.launch {
            courseTitles.postValue(getDisplayListUseCase.getListCourseUseCase())
        }
    }
}