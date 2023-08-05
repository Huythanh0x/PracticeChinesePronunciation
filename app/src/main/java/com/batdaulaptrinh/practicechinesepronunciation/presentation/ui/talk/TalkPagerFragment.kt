package com.batdaulaptrinh.practicechinesepronunciation.presentation.ui.talk

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity
import com.batdaulaptrinh.practicechinesepronunciation.databinding.FragmentTalkViewPagerBinding
import com.batdaulaptrinh.practicechinesepronunciation.util.string.TextUtil
import java.util.Locale

class TalkPagerFragment() : Fragment() {
    private var _binding: FragmentTalkViewPagerBinding? = null
    private val binding get() = _binding!!
    lateinit var tts: TextToSpeech
    lateinit var speech: NewSpeechEntity
    private val speechRecognitionContract = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val res: ArrayList<String>? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!res.isNullOrEmpty()) {
                onGetSpeechResult(res[0])
            } else {
                Log.e("recognizedText", "null or empty")
            }
        } else {
            Log.e("requestResult", "error")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTalkViewPagerBinding.inflate(inflater, container, false)
        tts = TextToSpeech(requireContext(), ttsListener)
        speech = arguments?.getParcelable("data")!!
        speech.apply {
            binding.tvEnglish.text = english
            binding.tvChinese.text = chinese
            binding.tvPinyin.text = pinyin
        }
        binding.apply {
            tvChinese.isVisible = false
            tvPinyin.isVisible = false
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun startListening() {
        if (!isRecordAudioPermissionGranted()) {
            requestRecordAudioPermission()
        } else {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.activity?.packageName)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1000)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Your Prompt")
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE, "zh-CN"
            )
            try {
                speechRecognitionContract.launch(intent)
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(), "Speech recognition failed: ${e.message}", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun setChineseVisibility(isVisible: Boolean) {
        binding.tvChinese.isVisible = isVisible
    }

    fun setPinyinVisibility(isVisible: Boolean) {
        binding.tvPinyin.isVisible = isVisible
    }

    fun speakOut() {
        tts.speak(speech.chinese, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun isRecordAudioPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (requestCode == RECORD_AUDIO_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    requireContext(), "GRANTED AUDIO PERMISSION SUCCESSFULLY", Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(), "NEED GRANTED AUDIO PERMISSION TO USE", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun requestInstallLanguage() {
        val installIntent = Intent()
        installIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
        startActivity(installIntent)
    }

    private fun requestRecordAudioPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.RECORD_AUDIO),
            RECORD_AUDIO_PERMISSION_REQUEST_CODE
        )
    }

    private val ttsListener = TextToSpeech.OnInitListener { status ->
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.SIMPLIFIED_CHINESE)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                requestInstallLanguage()
            }
        }
    }

    private fun onGetSpeechResult(chineseResult: String) {
        binding.tvChinese.isVisible = true
        binding.tvChinese.text =
            TextUtil.highlightDifferences(speech.chinese.filter { !it.isWhitespace() },
                chineseResult.filter { !it.isWhitespace() })
    }
}

private const val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 1001