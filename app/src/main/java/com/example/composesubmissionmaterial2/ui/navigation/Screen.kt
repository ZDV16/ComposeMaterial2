package com.example.composesubmissionmaterial2.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object About : Screen("about_page")
    object DetailPlayer : Screen("home/{playerId}") {
        fun createRoute(playerId: Long) = "home/$playerId"
    }
}
