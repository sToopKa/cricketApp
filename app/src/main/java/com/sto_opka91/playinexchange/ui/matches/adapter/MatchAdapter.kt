package com.sto_opka91.playinexchange.ui.matches.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.ItemMatchBinding
import com.sto_opka91.playinexchange.databinding.LocalMatchesItemBinding
import com.sto_opka91.playinexchange.ui.local_matches.adapter.LocalMatchesHolder

class MatchAdapter (
    private val onItemNavigateToScrollListener: (Match) -> Unit
) : ListAdapter<Match, MatchViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {

        val context = parent.context
        return MatchViewHolder(
            ItemMatchBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ),
            onItemNavigateToScrollListener
        )
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem == newItem
        }
    }
}