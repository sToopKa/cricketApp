package com.sto_opka91.playinexchange.ui.matches.adapter

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sto_opka91.playinexchange.R
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.ItemMatchBinding
import com.sto_opka91.playinexchange.databinding.LocalMatchesItemBinding

class MatchViewHolder (
    private val binding: ItemMatchBinding,
    private val onItemNavigateToScrollListener: (Match) -> Unit,

    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Match) {
        bindInfo(task)
    }

    private fun bindInfo(task: Match) = with(binding) {
        tvCityItem.text = task.city

        if(task.picture!=null){
            imPicture.setImageResource(task.picture)
        }else{
            if(task.photoUri!=""){
                imPicture.setImageURI(task.photoUri.toUri())
            }else{
                imPicture.setImageResource(R.drawable.ic_match_2)
            }
        }





        btnView.setOnClickListener {
            onItemNavigateToScrollListener.invoke(task)
        }

    }
}