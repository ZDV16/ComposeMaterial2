package com.example.composesubmissionmaterial2.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesubmissionmaterial2.data.PlayerRepository
import com.example.composesubmissionmaterial2.model.FavoritePlayer
import com.example.composesubmissionmaterial2.model.Player
import com.example.composesubmissionmaterial2.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailPlayerViewModel(
    private val repository: PlayerRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<FavoritePlayer>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FavoritePlayer>>
        get() = _uiState

    fun getPlayerById(playerId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getPlayerFavoriteById(playerId))
        }
    }

    fun addToCart(player: Player, count: Int) {
        viewModelScope.launch {
            repository.updateOrderReward(player.id, count)
        }
    }
}