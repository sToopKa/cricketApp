package com.sto_opka91.playinexchange.di

import android.content.Context
import androidx.room.Room
import com.sto_opka91.playinexchange.data.repositoryImpl.RoomRepositoryImpl
import com.sto_opka91.playinexchange.database.DatabasePlayInExchange
import com.sto_opka91.playinexchange.database.RoomDao
import com.sto_opka91.playinexchange.repository.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDi {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DatabasePlayInExchange {
        return Room.databaseBuilder(context, DatabasePlayInExchange::class.java, "database_play_in_exchange")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRoomDao(database: DatabasePlayInExchange): RoomDao {
        return database.roomDao()
    }

    @Provides
    @Singleton
    fun provideRoomRepository(roomDao: RoomDao): RoomRepository {
        return RoomRepositoryImpl(roomDao)
    }
}