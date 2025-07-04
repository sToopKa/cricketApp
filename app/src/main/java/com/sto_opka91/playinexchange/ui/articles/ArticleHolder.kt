package com.sto_opka91.playinexchange.ui.articles

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.sto_opka91.playinexchange.R
import com.sto_opka91.playinexchange.data.room.ArticleEntity
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.ItemArticlesBinding
import com.sto_opka91.playinexchange.databinding.ItemMatchBinding

class ArticleHolder (
    private val binding: ItemArticlesBinding,
    private val onItemNavigateToScrollListener: (ArticleEntity) -> Unit,

    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: ArticleEntity) {
        bindInfo(task)
    }

    private fun bindInfo(task: ArticleEntity) = with(binding) {
        tvTitleArticle.text = task.title
        tvArticleText.text = task.text

        if(task.picture!=null){
            imPicture.setImageResource(task.picture)
        }else{
            if(task.photoUri!=""){
                imPicture.setImageURI(task.photoUri.toUri())
            }else{
                imPicture.setImageResource(R.drawable.ic_match_2)
            }
        }


        itemCl.setOnClickListener {
            onItemNavigateToScrollListener.invoke(task)
        }

    }
}