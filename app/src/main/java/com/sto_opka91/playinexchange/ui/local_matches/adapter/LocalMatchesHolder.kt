package com.sto_opka91.playinexchange.ui.local_matches.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.LocalMatchesItemBinding

class LocalMatchesHolder(
    private val binding: LocalMatchesItemBinding,
    private val onItemNavigateToScrollListener: (Match) -> Unit,

    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Match) {
        bindInfo(task)
    }

    private fun bindInfo(task: Match) = with(binding) {
        tvCity.text = task.city
        tvCost.text = task.enter
        tvTime.text = task.time
        if(task.picture!=null){
            imPicture.setImageResource(task.picture)
        }



        btnFollow.setOnClickListener {
            onItemNavigateToScrollListener.invoke(task)
        }

    }
}