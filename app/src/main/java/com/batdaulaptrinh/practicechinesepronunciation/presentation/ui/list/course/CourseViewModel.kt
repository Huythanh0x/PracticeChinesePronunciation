package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.course

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.db_version.CheckUpdateUseCase
import com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist.GetDisplayListUseCase
import com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.remote.DownloadCourseDataUseCase
import com.batdaulaptrinh.practicechinesepronunciation.util.ExecutionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    val checkUpdateUseCase: CheckUpdateUseCase,
    val downloadCourseDataUseCase: DownloadCourseDataUseCase,
    private val getDisplayListUseCase: GetDisplayListUseCase
) :
    ViewModel() {
    val courseTitles = MutableLiveData<List<String>>()
    val downloadExecutionResult = MutableLiveData<ExecutionResult>()
    fun loadCourseTitles() {
        viewModelScope.launch {
            courseTitles.postValue(getDisplayListUseCase.getListCourseUseCase())
        }
    }

    fun tryUpdateDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkUpdateUseCase()) {
                Log.d("TAG remote", "fetching")
                downloadCourseDataUseCase("")
                downloadExecutionResult.postValue(ExecutionResult.Success())
            } else {
                downloadExecutionResult.postValue(ExecutionResult.Error(null))
            }
        }
    }
}