package com.sto_opka91.playinexchange.ui.local_matches.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.LocalMatchesItemBinding

class LocalMatchesAdapter (
    private val onItemNavigateToScrollListener: (Match) -> Unit
) : ListAdapter<Match, LocalMatchesHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalMatchesHolder {

        val context = parent.context
        return LocalMatchesHolder(
            LocalMatchesItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ),
            onItemNavigateToScrollListener
        )
    }

    override fun onBindViewHolder(holder: LocalMatchesHolder, position: Int) {
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