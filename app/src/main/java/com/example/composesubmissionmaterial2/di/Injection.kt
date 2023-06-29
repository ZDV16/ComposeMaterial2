package com.example.composesubmissionmaterial2.di

import com.example.composesubmissionmaterial2.data.PlayerRepository

object Injection {
    fun provideRepository(): PlayerRepository {
        return PlayerRepository.getInstance()
    }
}