package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.speech

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity
import com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist.GetDisplayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpeechViewModel @Inject constructor(private val getDisplayListUseCase: GetDisplayListUseCase) :
    ViewModel() {
    val speeches = MutableLiveData<List<NewSpeechEntity>>()
    fun loadSpeeches(lessonTile: String) {
        viewModelScope.launch {
            speeches.postValue(getDisplayListUseCase.getListVocabUseCase(lessonTile))
        }
    }
}