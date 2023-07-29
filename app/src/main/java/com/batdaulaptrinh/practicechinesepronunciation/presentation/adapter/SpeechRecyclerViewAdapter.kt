package com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.batdaulaptrinh.practicechinesepronunciation.R
import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity
import com.batdaulaptrinh.practicechinesepronunciation.databinding.DialogItemBinding
import com.batdaulaptrinh.practicechinesepronunciation.databinding.VocabularyItemBinding
import com.batdaulaptrinh.practicechinesepronunciation.presentation.adapter.diffutils.DiffUtilSpeechCallback

class SpeechRecyclerViewAdapter(
    private val speeches: MutableList<NewSpeechEntity>,
    private val itemCLickListener: (speech: NewSpeechEntity) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class VocabHolder(private val binding: VocabularyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(speech: NewSpeechEntity, itemCLickListener: (speech: NewSpeechEntity) -> Unit) {
            binding.root.setOnClickListener {
                itemCLickListener(speech)
            }
            binding.apply {
                tvEnglish.text = speech.english
                tvChinese.text = speech.chinese
                tvPinyin.text = speech.pinyin
                ivPreview.setBackgroundColor(binding.root.context.getColor(R.color.black))
            }
        }
    }

    class DialogHolder(private val binding: DialogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(speech: NewSpeechEntity, itemCLickListener: (speech: NewSpeechEntity) -> Unit) {
            binding.root.setOnClickListener {
                itemCLickListener(speech)
            }
            binding.apply {
                tvEnglish.text = speech.english
                tvChinese.text = speech.chinese
                tvPinyin.text = speech.pinyin
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (speeches[position].phrases_type == "vocabulary") VOCAB_TYPE
        else DIALOG_TYPE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if (viewType == VOCAB_TYPE) {
            val binding =
                VocabularyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return VocabHolder(binding)
        } else {
            val binding =
                DialogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DialogHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VocabHolder) {
            holder.bind(speeches[position], itemCLickListener)
        } else if (holder is DialogHolder) {
            holder.bind(speeches[position], itemCLickListener)
        }
    }

    override fun getItemCount() = speeches.size

    fun setList(newSpeeches: List<NewSpeechEntity>) {
        val diffCallback = DiffUtilSpeechCallback(speeches, newSpeeches)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        speeches.clear()
        speeches.addAll(newSpeeches)
        diffResult.dispatchUpdatesTo(this)
    }
}

const val VOCAB_TYPE = 0
const val DIALOG_TYPE = 1