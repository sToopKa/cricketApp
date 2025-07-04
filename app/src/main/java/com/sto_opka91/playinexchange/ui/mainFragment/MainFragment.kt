package com.sto_opka91.playinexchange.ui.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.sto_opka91.playinexchange.R
import com.sto_opka91.playinexchange.databinding.FragmentMainBinding
import com.sto_opka91.playinexchange.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLocalMatches.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_navigation_home)
        }

        binding.btnMyMatches.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_navigation_notifications)
        }

        binding.btnMatches.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_matchesFragment)
        }

        binding.btnCommunication.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_articleListFragment)
        }

        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_profileFragment)
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}