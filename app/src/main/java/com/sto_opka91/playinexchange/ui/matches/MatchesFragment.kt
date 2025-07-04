package com.sto_opka91.playinexchange.ui.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sto_opka91.playinexchange.R
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.FragmentLocalMatchesBinding
import com.sto_opka91.playinexchange.databinding.FragmentMatchesBinding
import com.sto_opka91.playinexchange.ui.MainViewModel
import com.sto_opka91.playinexchange.ui.local_matches.adapter.LocalMatchesAdapter
import com.sto_opka91.playinexchange.ui.matches.adapter.MatchAdapter
import com.sto_opka91.playinexchange.utils.LIST_MATCHES
import com.sto_opka91.playinexchange.utils.LIST_MY_MATCHES
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MatchesFragment : Fragment() {

    private var _binding: FragmentMatchesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private val adapterMatch by lazy { initMatchAdapter() }

    private fun initMatchAdapter(): MatchAdapter =
        MatchAdapter(
            onItemNavigateToScrollListener = { match ->
                findNavController().navigate(R.id.action_matchesFragment_to_followersFragment)
            })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)
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
        adapterMatch.submitList(LIST_MY_MATCHES)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}