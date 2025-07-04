package com.sto_opka91.playinexchange.ui.my_matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sto_opka91.playinexchange.R
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.FragmentMyMatchesBinding
import com.sto_opka91.playinexchange.ui.MainViewModel
import com.sto_opka91.playinexchange.ui.local_matches.adapter.LocalMatchesAdapter
import com.sto_opka91.playinexchange.utils.LIST_MATCHES
import com.sto_opka91.playinexchange.utils.LIST_MY_MATCHES
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyMatchesFragment : Fragment() {

    private var _binding: FragmentMyMatchesBinding? = null

    private val binding get() = _binding!!

    private val adapterMatch by lazy { initMatchAdapter() }

    private val viewModel: MainViewModel by activityViewModels()
    private fun initMatchAdapter(): MyMatchesAdapter =
        MyMatchesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMyMatchesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllMatches()
        observeViewModel()
        initRecyclerView()


        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_notifications_to_createMatchFragment)
        }
    }

    private fun initRecyclerView() = with(binding) {
        rcMatches.layoutManager = LinearLayoutManager(requireContext())
        rcMatches.adapter = adapterMatch
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.settingsState.collect { state ->
               adapterMatch.submitList(state.matches)
            }
        }
    }
}