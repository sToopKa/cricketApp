package com.sto_opka91.playinexchange.ui.my_matches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.ItemMyMatchesBinding
import com.sto_opka91.playinexchange.databinding.LocalMatchesItemBinding
import com.sto_opka91.playinexchange.ui.local_matches.adapter.LocalMatchesHolder

class MyMatchesAdapter (

) : ListAdapter<Match, MyMatchesHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMatchesHolder {

        val context = parent.context
        return MyMatchesHolder(
            ItemMyMatchesBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyMatchesHolder, position: Int) {
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