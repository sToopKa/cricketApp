package com.sto_opka91.playinexchange.ui.articles

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
import com.sto_opka91.playinexchange.data.room.ArticleEntity
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.FragmentArticleListBinding
import com.sto_opka91.playinexchange.ui.MainViewModel
import com.sto_opka91.playinexchange.ui.local_matches.adapter.LocalMatchesAdapter
import com.sto_opka91.playinexchange.utils.LIST_Article
import com.sto_opka91.playinexchange.utils.LIST_MATCHES
import com.sto_opka91.playinexchange.utils.LIST_MY_MATCHES
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticleListFragment : Fragment() {
    private var _binding: FragmentArticleListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private val adapterMatch by lazy { initMatchAdapter() }

    private fun initMatchAdapter(): ArticleAdapter =
        ArticleAdapter(
            onItemNavigateToScrollListener = { match ->
               viewModel.setArticle(match)
                findNavController().navigate(R.id.action_articleListFragment_to_articleFragment)
            })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllArticles()
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.floatingActionButton.setOnClickListener {
            viewModel.setArticle(null)
            findNavController().navigate(R.id.action_articleListFragment_to_articleFragment)
        }
        initRecyclerView()
        observeViewModel()
    }

    private fun initRecyclerView() {
        binding.rcMatches.layoutManager = LinearLayoutManager(requireContext())
        binding.rcMatches.adapter = adapterMatch
        adapterMatch.submitList(LIST_Article)
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.settingsState.collect { state ->
                val mutableList = mutableListOf<ArticleEntity>()
                LIST_Article.forEach {
                    mutableList.add(it)
                }
                if (state.articles!=null){
                    state.articles.forEach {
                        mutableList.add(it)
                    }
                }
                adapterMatch.submitList(mutableList)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}