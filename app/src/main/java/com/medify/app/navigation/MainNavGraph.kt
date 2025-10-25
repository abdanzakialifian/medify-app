package com.medify.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medify.app.presentation.dashboard.DashboardScreen
import com.medify.app.presentation.login.LoginScreen
import com.medify.app.presentation.profile.ProfileScreen
import com.medify.app.presentation.registration.RegistrationScreen

@Composable
fun MainNavGraph() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Login.route) {
            LoginScreen {
                navController.navigate(Screen.Registration.route)
            }
        }
        composable(route = Screen.Registration.route) {
            RegistrationScreen {
                navController.navigateUp()
            }
        }
        composable(route = Screen.Dashboard.route) {
            DashboardScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }
    }
}