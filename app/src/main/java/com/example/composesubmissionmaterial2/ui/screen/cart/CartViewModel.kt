package com.example.composesubmissionmaterial2.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesubmissionmaterial2.data.PlayerRepository
import com.example.composesubmissionmaterial2.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: PlayerRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderRewards() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderRewards()
                .collect { orderReward ->

                    _uiState.value = UiState.Success(CartState(orderReward))
                }
        }
    }

    fun updateOrderReward(playerId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderReward(playerId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderRewards()
                    }
                }
        }
    }
}