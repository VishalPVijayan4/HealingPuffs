package com.buildndeploy.healingpuffs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.buildndeploy.healingpuffs.ui.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") {
            OnboardingScreen(onContinue = {
                navController.navigate("home") {
                    popUpTo("onboarding") { inclusive = true }
                }
            })
        }

        composable("home") {
            HomeScreen(
                lastUrge = "45m ago",
                lastSmoke = "2h ago",
                onLogUrge = { navController.navigate("log_urge") },
                onLogSmoke = { navController.navigate("smoke") },
                onPatterns = { navController.navigate("patterns") }
            )
        }

        composable("log_urge") {
            LogUrgeScreen(
                onStartDelay = { navController.navigate("delay") },
                onBack = { navController.popBackStack() }
            )
        }

        composable("delay") {
            DelayScreen(
                onSmoke = { navController.navigate("smoke") },
                onBack = { navController.popBackStack() }
            )
        }

        composable("smoke") {
            SmokeScreen(
                onContinue = { navController.navigate("home") },
                onBack = { navController.popBackStack() }
            )
        }

        composable("patterns") {
            PatternsScreen(
                patterns = listOf("Most urges: 8-10 PM", "Top trigger: Stress"),
                onBack = { navController.popBackStack() }
            )
        }
    }
}
