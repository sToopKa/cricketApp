package com.sto_opka91.playinexchange.ui.local_matches

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

import com.sto_opka91.playinexchange.databinding.FragmentLocalMatchesBinding
import com.sto_opka91.playinexchange.ui.MainViewModel
import com.sto_opka91.playinexchange.ui.local_matches.adapter.LocalMatchesAdapter
import com.sto_opka91.playinexchange.utils.LIST_MATCHES
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocalMatchesFragment : Fragment() {

    private var _binding: FragmentLocalMatchesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private val adapterMatch by lazy { initMatchAdapter() }

    private fun initMatchAdapter(): LocalMatchesAdapter =
        LocalMatchesAdapter(
            onItemNavigateToScrollListener = { match ->
                lifecycleScope.launch {
                    viewModel.saveMatch(match)
                    delay(300)
                    findNavController().navigate(R.id.action_navigation_home_to_navigation_notifications)
                }
            })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocalMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initRecyclerView() = with(binding) {
        rcMatches.layoutManager = LinearLayoutManager(requireContext())
        rcMatches.adapter = adapterMatch
        adapterMatch.submitList(LIST_MATCHES)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}