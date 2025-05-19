package com.example.bhagavatgeeta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.example.bhagavatgeeta.databinding.FragmentHomeBinding
import com.example.bhagavatgeeta.model.chaptersItem
import com.example.bhagavatgeeta.view.adapter.AdapterFile
import com.example.bhagavatgeeta.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapterFile: AdapterFile


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        checkInternetConnectivity()
        getAllChapter()




        return binding.root
    }

    private fun checkInternetConnectivity() {
        val networkManager = NetworkManager(requireContext())
        networkManager.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.tvShowMessge.visibility = View.GONE
                getAllChapter()

            } else {
                binding.tvShowMessge.visibility = View.VISIBLE

            }
        }


    }

    private fun getAllChapter() {

        lifecycleScope.launch {
            viewModel.getAllChapter().collect { chapterlist ->
                adapterFile = AdapterFile(::onChapterIVClick)
                binding.recycleView.adapter = adapterFile
                adapterFile.differ.submitList(chapterlist)


            }
        }

    }

    private fun onChapterIVClick(chaptersItem: chaptersItem) {

        val bundle = Bundle()
        bundle.putInt("chapter_number", chaptersItem.chapter_number)
        bundle.putString("chapter_name", chaptersItem.name_translated)
        bundle.putString("chapter_des", chaptersItem.chapter_summary)
        bundle.putInt("verse_count", chaptersItem.verses_count)



        findNavController().navigate(R.id.action_homeFragment_to_versesFragment, bundle)


    }


}




