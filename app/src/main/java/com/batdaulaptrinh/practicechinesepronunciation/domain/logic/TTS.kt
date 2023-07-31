package com.batdaulaptrinh.practicechinesepronunciation.domain.logic

import android.app.Activity
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.widget.Toast
import java.util.Locale


class TTS(
    private val activity: Activity,
    private val message: String
) : TextToSpeech.OnInitListener {

    private val tts = TextToSpeech(activity, this)

    override fun onInit(i: Int) {
        if (i == TextToSpeech.SUCCESS) {
            val localeZh = Locale("zh", "CN", "Hans")
            val result = tts.setLanguage(localeZh)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                val installIntent = Intent()
                installIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
                activity.startActivity(installIntent)
            } else {
                speakOut(message)
            }
        } else {
            Toast.makeText(activity, "Initialization Failed!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun speakOut(message: String) {
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}
