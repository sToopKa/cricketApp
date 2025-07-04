package com.sto_opka91.playinexchange.repository

import com.sto_opka91.playinexchange.data.room.ArticleEntity
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.data.room.UserEntity

interface RoomRepository {
suspend fun saveMatch(match: Match)
suspend fun getAllMatches():List<Match>

    suspend fun saveArticle(match: ArticleEntity)
    suspend fun getAllArticles():List<ArticleEntity>

    suspend fun saveUser(match: UserEntity)
    suspend fun getUser():List<UserEntity>
    suspend fun clearUser()

}