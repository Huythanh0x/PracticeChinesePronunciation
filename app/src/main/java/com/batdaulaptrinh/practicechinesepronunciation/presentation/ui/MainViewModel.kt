package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui

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
class MainViewModel @Inject constructor(
    val checkUpdateUseCase: CheckUpdateUseCase,
    val downloadCourseDataUseCase: DownloadCourseDataUseCase,
    val getDisplayListUseCase: GetDisplayListUseCase
) : ViewModel() {
    val downloadResult = MutableLiveData<ExecutionResult?>()
    fun tryUpdateDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkUpdateUseCase()) {
                Log.d("TAG remote", "fetching")
                downloadCourseDataUseCase("")
            }
        }
    }
}