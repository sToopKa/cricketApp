package com.sto_opka91.playinexchange.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sto_opka91.playinexchange.R
import com.sto_opka91.playinexchange.databinding.ActivityMainBinding
import com.sto_opka91.playinexchange.databinding.FragmentLocalMatchesBinding


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({

             findNavController().navigate(R.id.action_splashFragment_to_navigation_dashboard)
        }, 2000)
    }


}