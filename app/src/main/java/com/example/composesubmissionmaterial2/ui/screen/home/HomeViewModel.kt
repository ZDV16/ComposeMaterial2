package com.example.composesubmissionmaterial2.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesubmissionmaterial2.data.PlayerRepository
import com.example.composesubmissionmaterial2.model.FavoritePlayer
import com.example.composesubmissionmaterial2.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PlayerRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<FavoritePlayer>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<FavoritePlayer>>>
        get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllPlayers()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}