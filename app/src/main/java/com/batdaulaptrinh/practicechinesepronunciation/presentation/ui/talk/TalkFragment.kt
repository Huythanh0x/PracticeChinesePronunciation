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
import androidx.navigation.fragment.navArgs
import com.batdaulaptrinh.practicechinesepronunciation.databinding.FragmentTalkBinding
import com.batdaulaptrinh.practicechinesepronunciation.util.string.TextUtil
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class TalkFragment() : Fragment() {
    private var _binding: FragmentTalkBinding? = null
    private val navArgs: TalkFragmentArgs by navArgs()
    private val binding get() = _binding!!
    lateinit var tts: TextToSpeech
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
        _binding = FragmentTalkBinding.inflate(inflater, container, false)
        tts = TextToSpeech(requireContext(), ttsListener)
        val lessonTitle = navArgs.speech.lessonTitle.split(" ").subList(0, 2).joinToString(": ")
        binding.tvLessonTitle.text = lessonTitle
        navArgs.speech.apply {
            binding.tvEnglish.text = english
            binding.tvChinese.text = chinese
            binding.tvPinyin.text = pinyin
        }
        binding.btnMicro.setOnClickListener {
            startListening()
        }
        binding.btnSpeaker.setOnClickListener {
            tts.speak(navArgs.speech.chinese, TextToSpeech.QUEUE_FLUSH, null, "")
        }
        binding.btnChinese.setOnClickListener {
            binding.btnChinese.alpha = 1.0f
            binding.tvChinese.isVisible = true
        }
        binding.btnPinyin.setOnClickListener {
            binding.btnPinyin.alpha = 1.0f
            binding.tvPinyin.isVisible = true
        }
        binding.btnNext.setOnClickListener {
            //TODO
        }
        binding.btnPrevious.setOnClickListener {
            //TODO
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startListening() {
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
                    requireContext(),
                    "Speech recognition failed: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
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
        binding.btnChinese.alpha = 1.0f
        binding.tvChinese.text =
            TextUtil.highlightDifferences(navArgs.speech.chinese.filter { !it.isWhitespace() },
                chineseResult.filter { !it.isWhitespace() })
    }
}

private const val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 1001