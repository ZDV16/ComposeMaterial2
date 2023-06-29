package com.example.composesubmissionmaterial2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composesubmissionmaterial2.data.PlayerRepository
import com.example.composesubmissionmaterial2.ui.screen.cart.CartViewModel
import com.example.composesubmissionmaterial2.ui.screen.detail.DetailPlayerViewModel
import com.example.composesubmissionmaterial2.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: PlayerRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailPlayerViewModel::class.java)) {
            return DetailPlayerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}