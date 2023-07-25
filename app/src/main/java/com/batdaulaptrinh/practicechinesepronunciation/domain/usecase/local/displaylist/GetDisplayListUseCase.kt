package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist

import javax.inject.Inject

class GetDisplayListUseCase @Inject constructor(
    val getListCourseUseCase: GetListCourseUseCase,
    val getListWeekUseCase: GetListWeekUseCase,
    val getListLessonUseCase: GetListLessonUseCase,
    val getListVocabUseCase: GetListVocabUseCase
)
