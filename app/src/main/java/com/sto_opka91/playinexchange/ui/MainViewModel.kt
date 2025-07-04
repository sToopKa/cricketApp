package com.sto_opka91.playinexchange.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sto_opka91.playinexchange.AppHolader
import com.sto_opka91.playinexchange.data.room.ArticleEntity
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.data.room.UserEntity
import com.sto_opka91.playinexchange.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {



    private val _settingsState = MutableStateFlow(AppHolader())
    val settingsState = _settingsState.asStateFlow()

    private val _sharedVariable = MutableLiveData<ArticleEntity?>(null)
    val sharedVariable: LiveData<ArticleEntity?> = _sharedVariable

    fun setArticle(value: ArticleEntity?) {
        _sharedVariable.value = value
    }

    fun saveMatch(match: Match) {
        viewModelScope.launch {
            roomRepository.saveMatch(match)

        }
    }

    fun saveArticle(match: ArticleEntity) {
        viewModelScope.launch {
            roomRepository.saveArticle(match)
        }
    }

    fun getAllMatches() {
        viewModelScope.launch {
            val matches = roomRepository.getAllMatches()
            _settingsState.update { it.copy(matches = matches) }
        }
    }

    fun getAllArticles() {
        viewModelScope.launch {
            val articles = roomRepository.getAllArticles()
            _settingsState.update { it.copy(articles = articles) }
        }
    }

    fun saveUser(match: UserEntity) {
        viewModelScope.launch {
            roomRepository.saveUser(match)
        }
    }

    fun clearUser() {
        viewModelScope.launch {
            roomRepository.clearUser()
        }
    }

    fun getUser() {
        viewModelScope.launch {
            val user = roomRepository.getUser()
            _settingsState.update {
                it.copy(user = user)
            }
        }
    }
}