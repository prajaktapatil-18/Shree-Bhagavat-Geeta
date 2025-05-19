package com.example.bhagavatgeeta

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bhagavatgeeta.databinding.FragmentVersesBinding
import com.example.bhagavatgeeta.view.adapter.VerseAdapter
import com.example.bhagavatgeeta.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class VersesFragment : Fragment() {

    private lateinit var binding: FragmentVersesBinding
    private lateinit var adapterVerse: VerseAdapter
    var chapterNumber = 0


    var isExplained = false

    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentVersesBinding.inflate(layoutInflater)
        getChapterDetails()
        checkInternetConnectivity()
        getVerses()
        return (binding.root)
    }




    private fun checkInternetConnectivity() {
        val networkManager = NetworkManager(requireContext())
        networkManager.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.tvShowMessge.visibility = View.GONE
              getVerses()

            } else {
                binding.tvShowMessge.visibility = View.VISIBLE
            }
        }


    }

    //  check internet fun    remian
    private fun getChapterDetails() {
        val bundle = arguments
        chapterNumber = bundle?.getInt("chapter_number")!!
        binding.tvChapterNo.text = "Chapter ${bundle?.getInt("chapter_number")}"
        binding.tvChapterName.text = bundle?.getString("chapter_name")
        binding.tvVerseCountNo.text = "Verse ${bundle.getInt("verse_count")}"

        //binding.versePara.text = bundle?.getString("chapter_des")


        binding.tvReadMore.setOnClickListener{
            if (isExplained){
                binding.versePara.maxLines =  Int.MAX_VALUE
                binding.versePara.text = bundle.getString("chapter_des")
                binding.tvReadMore.text = "read less"


            }else{
                binding.versePara.maxLines = 3 // Show only first 3 lines or any number you prefer
                binding.versePara.text = bundle?.getString("chapter_des")
                binding.tvReadMore.text = "Read More"
            }

            isExplained = !isExplained
        }



    }
    private fun getVerses() {
        adapterVerse = VerseAdapter(::onVersesItemVClick)
        binding.recycleViewVerse.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleViewVerse.adapter = adapterVerse

        lifecycleScope.launch {
            viewModel.getVerse(chapterNumber).collect { verseI ->
                val verseList = arrayListOf<String>()

                Log.d("VersesFragment", "Total verses fetched: ${verseList.size}")


                for (currentVerse in verseI) {
                    for (verses in currentVerse.translations) {
                        if (verses.language == "english") {
                            verseList.add(verses.description)
                            break
                        }
                    }
                }

                adapterVerse.differ.submitList(verseList)
            }
        }
    }



    private fun onVersesItemVClick(verseNumber:Int, verse :String){



        val bundle = Bundle()
        bundle.putInt("chapterNum", chapterNumber)
        bundle.putInt("verseNum",verseNumber)



        findNavController().navigate(R.id.action_versesFragment_to_verseDetailFragment,bundle)



    }

}
