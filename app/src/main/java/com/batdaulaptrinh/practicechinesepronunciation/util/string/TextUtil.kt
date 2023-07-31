package com.batdaulaptrinh.practicechinesepronunciation.util.string

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log

object TextUtil {
    fun highlightDifferences(originalText: String, userText: String): Spannable {
        val ignoreChars =
            setOf('.', '!', '?', ',', ';', ':', ' ', 65311.toChar(), 65281.toChar(), 12290.toChar())
        val spannableBuilder = SpannableStringBuilder()
        var start = 0
        while (start < originalText.length && start < userText.length) {
            if (originalText[start] == userText[start] || originalText[start] in ignoreChars) {
                spannableBuilder.append(
                    originalText[start].toString(), ForegroundColorSpan(Color.GREEN), 0
                )
            } else {
                spannableBuilder.append(
                    originalText[start].toString(), ForegroundColorSpan(Color.RED), 0
                )
            }
            start++
        }
        while (start < originalText.length) {
            if (ignoreChars.contains(originalText[start])) {
                spannableBuilder.append(
                    originalText[start].toString(), ForegroundColorSpan(Color.GREEN), 0
                )
            } else {
                spannableBuilder.append(
                    originalText[start].toString(), ForegroundColorSpan(Color.RED), 0
                )
            }
            start++
        }
        return spannableBuilder
    }
}