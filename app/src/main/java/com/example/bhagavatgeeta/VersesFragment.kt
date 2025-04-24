package com.example.bhagavatgeeta

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagavatgeeta.databinding.FragmentVersesBinding
import com.example.bhagavatgeeta.view.adapter.VerseAdapter
import com.example.bhagavatgeeta.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class VersesFragment : Fragment() {

    private lateinit var binding: FragmentVersesBinding
    private lateinit var adapter: VerseAdapter
    var chapterNumber = 0


    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentVersesBinding.inflate(layoutInflater)
        getChapterDetails()
        getVerses()
        return (binding.root)
    }

    private fun getChapterDetails() {
        val bundle = arguments
        chapterNumber = bundle?.getInt("chapter_number")!!
        binding.tvChapterNo.text = "Chapter ${bundle?.getInt("chapter_number")}"
        binding.tvChapterName.text = bundle?.getString("chapter_name")
        binding.tvVerseCountNo.text = "Verse ${bundle.getInt("verse_count")}"

        binding.tvReadMore.setOnClickListener{
            binding.versePara.text = bundle?.getString("chapter_des")
            }




    }

    private fun getVerses() {
        lifecycleScope.launch {
            viewModel.getVerse(chapterNumber).collect {
//                for (i in it) {
//                    Log.d("tag", i.toString())
//                }
                adapter = VerseAdapter()
                binding.recycleViewVerse.adapter = adapter
                val verseList = arrayListOf<String>()


                for (currentverse in it){
                    for (verses in currentverse.translations){
                        if (verses.language== "english"){
                            verseList.add(verses.description)
                            break
                        }
                    }
                }

                adapter.differ.submitList(verseList)




            }
        }
    }


}
