package com.example.composesubmissionmaterial2.data

import com.example.composesubmissionmaterial2.model.FavoritePlayer
import com.example.composesubmissionmaterial2.model.PlayerDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class PlayerRepository {
    private val favoritePlayer = mutableListOf<FavoritePlayer>()

    init {
        if (favoritePlayer.isEmpty()) {
            PlayerDataSource.listPlayer.forEach {
                favoritePlayer.add(FavoritePlayer(it, 0))
            }
        }
    }

    fun getAllPlayers(): Flow<List<FavoritePlayer>> {
        return flowOf(favoritePlayer)
    }

    fun getPlayerFavoriteById(playerId: Long): FavoritePlayer {
        return favoritePlayer.first {
            it.player.id == playerId
        }
    }

    fun updateOrderReward(playerId: Long, newCountValue: Int): Flow<Boolean> {
        val index = favoritePlayer.indexOfFirst { it.player.id == playerId }
        val result = if (index >= 0) {
            val orderPlayer = favoritePlayer[index]
            favoritePlayer[index] =
                orderPlayer.copy(player = orderPlayer.player, merchCount = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderRewards(): Flow<List<FavoritePlayer>> {
        return getAllPlayers()
            .map { orderRewards ->
                orderRewards.filter { orderReward ->
                    orderReward.merchCount != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: PlayerRepository? = null

        fun getInstance(): PlayerRepository =
            instance ?: synchronized(this) {
                PlayerRepository().apply {
                    instance = this
                }
            }
    }
}
