package com.example.bhagavatgeeta

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.bhagavatgeeta.databinding.FragmentHomeBinding
import com.example.bhagavatgeeta.view.adapter.AdapterFile
import com.example.bhagavatgeeta.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var  binding :FragmentHomeBinding
private val viewModel:MainViewModel by viewModels()
     private lateinit var adapterFile: AdapterFile


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

  getAllChapter()
    return binding.root
    }

    private fun getAllChapter() {

lifecycleScope.launch {
    viewModel.getAllChapter().collect {chapterlist->

     adapterFile = AdapterFile()
        binding.recycleView.adapter =adapterFile
        adapterFile.differ.submitList(chapterlist)

    }
}


    }


}