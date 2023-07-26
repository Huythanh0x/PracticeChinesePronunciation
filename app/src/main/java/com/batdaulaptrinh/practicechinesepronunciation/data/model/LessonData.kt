package com.batdaulaptrinh.practicechinesepronunciation.data.model

class LessonData(
    val lessonTitle: String,
    val speeches: List<String>,
    val isLessonCompleted: Boolean = false
)