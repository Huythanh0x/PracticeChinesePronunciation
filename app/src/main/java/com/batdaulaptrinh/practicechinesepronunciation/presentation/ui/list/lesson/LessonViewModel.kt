package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.lesson

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batdaulaptrinh.practicechinesepronunciation.data.model.LessonData
import com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist.GetDisplayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(private val getDisplayListUseCase: GetDisplayListUseCase) :
    ViewModel() {
    val lessons = MutableLiveData<List<LessonData>>()
    fun loadLessons(weekTitle: String) {
        viewModelScope.launch {
            val lessonTitles = getDisplayListUseCase.getListLessonUseCase(weekTitle)
            val lessonSource =
                lessonTitles.map { lessonTitle ->
                    LessonData(
                        lessonTitle,
                        getDisplayListUseCase.getListVocabUseCase(lessonTitle).map { it.chinese }
                    )
                }
            lessons.postValue(lessonSource)
        }
    }
}