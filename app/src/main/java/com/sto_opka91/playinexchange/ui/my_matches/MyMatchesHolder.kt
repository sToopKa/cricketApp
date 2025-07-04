package com.sto_opka91.playinexchange.ui.my_matches

import androidx.recyclerview.widget.RecyclerView
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.ItemMyMatchesBinding
import com.sto_opka91.playinexchange.databinding.LocalMatchesItemBinding

class MyMatchesHolder (
    private val binding: ItemMyMatchesBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Match) {
        bindInfo(task)
    }

    private fun bindInfo(task: Match) = with(binding) {
        tvCity.text = task.city
        tvTime.text = task.time
        tvMatchTitle.text = task.vs


    }
}