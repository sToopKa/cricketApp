package com.sto_opka91.playinexchange

import com.sto_opka91.playinexchange.data.room.ArticleEntity
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.data.room.UserEntity

data class AppHolader(

    val matches : List<Match>? = null,
    val articles : List<ArticleEntity>? = null,
    val user : List<UserEntity>? = null


)
