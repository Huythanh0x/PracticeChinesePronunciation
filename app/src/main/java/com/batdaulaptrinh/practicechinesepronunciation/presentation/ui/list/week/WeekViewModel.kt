package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.list.week

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batdaulaptrinh.practicechinesepronunciation.data.model.LessonData
import com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist.GetDisplayListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeekViewModel @Inject constructor(private val getDisplayListUseCase: GetDisplayListUseCase) :
    ViewModel() {
    val weeks = MutableLiveData<HashMap<String, List<LessonData>>>()
    fun loadWeekData(courseTitle: String) {
        viewModelScope.launch {
            val weekTitles = getDisplayListUseCase.getListWeekUseCase(courseTitle)
            val weekMap = HashMap<String, List<LessonData>>()
            weekTitles.forEach { weekTitle ->
                weekMap[weekTitle] = getListLessonData(weekTitle)
            }
            weeks.postValue(weekMap)
        }
    }

    private suspend fun getListLessonData(weekTitle: String): List<LessonData> {
        val lessonTitles = getDisplayListUseCase.getListLessonUseCase(weekTitle)
        val lessonSource = lessonTitles.map { lessonTitle ->
            LessonData(lessonTitle,
                getDisplayListUseCase.getListVocabUseCase(lessonTitle).map { it.chinese })
        }
        return lessonSource
    }
}