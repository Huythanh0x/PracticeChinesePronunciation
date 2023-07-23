package com.batdaulaptrinh.practicechinesepronunciation.util

sealed class ExecutionResult(message: String? = null) {
    class Success : ExecutionResult()
    class Error(val message: String?) : ExecutionResult(message)
}