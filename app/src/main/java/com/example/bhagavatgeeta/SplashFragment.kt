package com.example.bhagavatgeeta

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bhagavatgeeta.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSplashBinding.inflate(layoutInflater)

        Handler(Looper.getMainLooper()).postDelayed({

            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)

        }, 3000)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}