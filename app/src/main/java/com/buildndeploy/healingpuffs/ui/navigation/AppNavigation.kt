package com.buildndeploy.healingpuffs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.buildndeploy.healingpuffs.ui.screens.*
import com.buildndeploy.healingpuffs.ui.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

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
            val viewModel: HomeViewModel = hiltViewModel()
            val lastUrge by viewModel.lastUrge.collectAsState()
            val lastSmoke by viewModel.lastSmoke.collectAsState()

            HomeScreen(
                lastUrge = lastUrge?.let { formatTimestamp(it.timestamp) },  // ✅ Convert to String
                lastSmoke = lastSmoke?.let { formatTimestamp(it.timestamp) },  // ✅ Convert to String
                onLogUrge = { navController.navigate("log_urge") },
                onLogSmoke = { navController.navigate("smoke") },
                onPatterns = { navController.navigate("patterns") },
                onSettings = { navController.navigate("settings") }
            )
        }

        composable("log_urge") {
            val viewModel: HomeViewModel = hiltViewModel()
            LogUrgeScreen(
                onStartDelay = {
                    viewModel.logUrge()
                    navController.navigate("delay")
                },
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
            val viewModel: HomeViewModel = hiltViewModel()
            SmokeScreen(
                onContinue = {
                    viewModel.logSmoke()
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("patterns") {
            val viewModel: HomeViewModel = hiltViewModel()
            val patterns by viewModel.patterns.collectAsState()

            PatternsScreen(
                patterns = patterns,
                onBack = { navController.popBackStack() }
            )
        }

        composable("settings") {
            SettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}

// ✅ Helper function to format timestamp
private fun formatTimestamp(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp

    return when {
        diff < 60_000 -> "Just now"  // Less than 1 minute
        diff < 3_600_000 -> "${diff / 60_000} min ago"  // Less than 1 hour
        diff < 86_400_000 -> "${diff / 3_600_000} hours ago"  // Less than 24 hours
        diff < 604_800_000 -> "${diff / 86_400_000} days ago"  // Less than 7 days
        else -> {
            // Format as date
            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            dateFormat.format(Date(timestamp))
        }
    }
}
