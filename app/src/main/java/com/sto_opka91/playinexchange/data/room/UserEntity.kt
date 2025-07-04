package com.sto_opka91.playinexchange.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val tg: String,
    val photoUri: String = ""
)
