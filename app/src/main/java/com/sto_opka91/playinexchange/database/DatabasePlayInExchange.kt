package com.sto_opka91.playinexchange.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sto_opka91.playinexchange.data.room.ArticleEntity
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.data.room.UserEntity

@Database(entities = [Match::class,ArticleEntity::class, UserEntity::class], version =4, exportSchema = false)
abstract class DatabasePlayInExchange: RoomDatabase() {
    abstract fun roomDao(): RoomDao
}