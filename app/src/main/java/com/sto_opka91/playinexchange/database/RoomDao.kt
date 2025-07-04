package com.sto_opka91.playinexchange.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sto_opka91.playinexchange.data.room.ArticleEntity
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.data.room.UserEntity

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createMatch(user: Match)

    @Query("SELECT * FROM `match`")
    suspend fun getAllMatch(): List<Match>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createArticle(user: ArticleEntity)

    @Query("SELECT * FROM `article`")
    suspend fun getAllArticles(): List<ArticleEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(user: UserEntity)

    @Query("SELECT * FROM `user`")
    suspend fun getUser(): List<UserEntity>

    @Query("DELETE FROM user")
    suspend fun clearUser()

}