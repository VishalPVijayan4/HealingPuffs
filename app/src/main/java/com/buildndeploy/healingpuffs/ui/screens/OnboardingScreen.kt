package com.buildndeploy.healingpuffs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buildndeploy.healingpuffs.ui.theme.NetflixBlack
import com.buildndeploy.healingpuffs.ui.theme.NetflixRed
import com.buildndeploy.healingpuffs.ui.theme.TextWhite

@Composable
fun OnboardingScreen(
    onContinue: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NetflixBlack)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "This app doesn't punish you.",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "You can smoke. You can log it. Nothing resets.",
                fontSize = 18.sp,
                color = TextWhite.copy(alpha = 0.8f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = onContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NetflixRed)
            ) {
                Text("Continue", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
