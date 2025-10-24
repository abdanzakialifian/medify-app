package com.medify.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medify.app.presentation.dashboard.DashboardScreen
import com.medify.app.presentation.login.LoginScreen
import com.medify.app.presentation.profile.ProfileScreen
import com.medify.app.presentation.registration.RegistrationScreen

@Composable
fun MainNavGraph(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()

    NavHost(modifier = modifier, navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Login.route) {
            LoginScreen()
        }
        composable(route = Screen.Registration.route) {
            RegistrationScreen()
        }
        composable(route = Screen.Dashboard.route) {
            DashboardScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }
    }
}