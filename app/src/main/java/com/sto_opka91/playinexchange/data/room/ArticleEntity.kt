package com.sto_opka91.playinexchange.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val picture: Int? = null,
    val title: String,
    val text: String,
    val photoUri: String = ""
)
