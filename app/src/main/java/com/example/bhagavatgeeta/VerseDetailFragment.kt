package com.example.bhagavatgeeta

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.bhagavatgeeta.databinding.FragmentVerseDetailBinding
import com.example.bhagavatgeeta.model.Commentary
import com.example.bhagavatgeeta.model.Translation
import com.example.bhagavatgeeta.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class VerseDetailFragment : Fragment() {

    private lateinit var binding: FragmentVerseDetailBinding
    private val viewModel: MainViewModel by viewModels()
    private var chapterNum = 0
    private var verseNum = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVerseDetailBinding.inflate(layoutInflater)
        getVerseNumber()
        getVerseDetail()
        // Inflate the layout for this fragment
        return binding.root
    }


    fun getVerseNumber() {

        val bundle = arguments
        chapterNum = bundle?.getInt("chapterNum")!!
        verseNum = bundle?.getInt("verseNum")!!

        binding.versecount.text = "|| $chapterNum. $verseNum ||"


// "https://bhagavad-gita3.p.rapidapi.com/v2/chapters/1/verses/1/


    }


    @SuppressLint("SetTextI18n")
    fun getVerseDetail() {
        lifecycleScope.launch {
            viewModel.getVerseData(chapterNum, verseNum).collect { verse ->


                binding.tvShlok.text = verse.text
                binding.verseDes1.text = verse.transliteration
                binding.verseDes2.text = verse.word_meanings


                val englishTranslation = arrayListOf<Translation>()
                for (i in verse.translations) {
                    if (i.language == "english") englishTranslation.add(i)
                }

                val englishTrasnlationListSize = englishTranslation.size

                if (englishTranslation.isNotEmpty()) {
                    binding.tvAuthorName.text = "Author : "+ englishTranslation[0].author_name
                    binding.tvTransDes1.text = englishTranslation[0].description

                    if (englishTrasnlationListSize == 1) {
                        binding.btnNext.visibility = View.GONE
                    }


                    var i = 0
                    binding.btnNext.setOnClickListener {
                        if (i < englishTrasnlationListSize - 1) {
                            i++
                            binding.tvAuthorName.text = englishTranslation[i].author_name
                            binding.tvTransDes1.text = englishTranslation[i].description
                            binding.btnBack.visibility = View.VISIBLE



                            if (i == englishTrasnlationListSize - 1) {
                                binding.btnNext.visibility = View.GONE

                            }
                        }

                        binding.btnBack.setOnClickListener {
                            if (i > 0) {
                                i--
                                binding.tvAuthorName.text = englishTranslation[i].author_name
                                binding.tvTransDes1.text = englishTranslation[i].description
                                binding.btnNext.visibility = View.VISIBLE
                                if (i == 0) binding.btnBack.visibility = View.GONE
                            }
                        }
                    }

                }


                // for commenatry fetching des and author


                val englishCommetary = arrayListOf<Commentary>()
                for (i in verse.commentaries) {
                    if (i.language == "hindi") englishCommetary.add(i)

                }

                val englishCommentaryListSize = englishCommetary.size
                if (englishCommetary.isNotEmpty()) {

                    Log.d("comm","fetching data")
                    binding.tvCommentaryAuthorName.text = "Author : " + englishCommetary[0].author_name
                    binding.tvCommentaryDes1.text = englishCommetary[0].descriptionCom

                    if (englishCommentaryListSize == 1) {
                        binding.btnNextComm.visibility = View.GONE
                    }


                    var k = 0
                    binding.btnNextComm.setOnClickListener {

                        if (k < englishCommentaryListSize - 1) {
                            k++
                            binding.tvCommentaryAuthorName.text = englishCommetary[k].author_name
                            binding.tvCommentaryDes1.text = englishCommetary[k].descriptionCom
                            binding.btnBackComm.visibility = View.VISIBLE


                            if (k == englishCommentaryListSize - 1) {
                                binding.btnNextComm.visibility = View.GONE
                            }

                        }

                        binding.btnBackComm.setOnClickListener {
                            if (k > 0) {
                                k--
                                binding.tvCommentaryAuthorName.text = englishCommetary[k].author_name
                                binding.tvCommentaryDes1.text = englishCommetary[k].descriptionCom
                                binding.btnNextComm.visibility = View.VISIBLE

                                if (k == 0) binding.btnBackComm.visibility = View.GONE
                            }
                        }


                    }
                }


            }

        }
    }
}

