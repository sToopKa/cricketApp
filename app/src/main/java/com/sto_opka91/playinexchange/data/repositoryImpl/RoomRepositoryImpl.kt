package com.sto_opka91.playinexchange.data.repositoryImpl

import com.sto_opka91.playinexchange.data.room.ArticleEntity
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.data.room.UserEntity
import com.sto_opka91.playinexchange.database.RoomDao
import com.sto_opka91.playinexchange.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl@Inject constructor(
    private val roomDao: RoomDao
): RoomRepository {
    override suspend fun saveMatch(match: Match) {
        val matches = roomDao.getAllMatch()
         var flag = false
        matches.forEach {
            if(match.picture==it.picture && match.city==it.city && match.time==it.time){
                flag=true
            }
        }
        if(!flag){
            roomDao.createMatch(match)
        }

    }

    override suspend fun getAllMatches():List<Match> {
       return roomDao.getAllMatch()
    }

    override suspend fun saveArticle(match: ArticleEntity) {
        roomDao.createArticle(match)
    }

    override suspend fun getAllArticles(): List<ArticleEntity> {
        return roomDao.getAllArticles()
    }

    override suspend fun saveUser(match: UserEntity) {
        roomDao.createUser(match)
    }

    override suspend fun getUser(): List<UserEntity> {
        return roomDao.getUser()
    }

    override suspend fun clearUser() {
       roomDao.clearUser()
    }

}