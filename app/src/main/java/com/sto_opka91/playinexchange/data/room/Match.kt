package com.sto_opka91.playinexchange.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match")
data class Match(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val picture: Int? = null,
    val city: String,
    val time: String,
    val enter: String,
    val photoUri: String = ""

)
