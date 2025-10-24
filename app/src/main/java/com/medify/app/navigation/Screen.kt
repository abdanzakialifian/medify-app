package com.medify.app.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login_screen")
    data object Registration : Screen("registration_screen")
    data object Dashboard : Screen("dashboard_screen")
    data object Profile : Screen("profile_screen")
}