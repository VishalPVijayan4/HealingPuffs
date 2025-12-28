package com.buildndeploy.healingpuffs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buildndeploy.healingpuffs.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun DelayScreen(
    onSmoke: () -> Unit,
    onBack: () -> Unit
) {
    var timeLeft by remember { mutableIntStateOf(600) } // 10 minutes

    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NetflixBlack),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Timer Circle
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(NetflixDarkGrey),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${timeLeft / 60}:${String.format("%02d", timeLeft % 60)}",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Breathe 4-7-8",
                fontSize = 24.sp,
                color = TextWhite,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Inhale 4s → Hold 7s → Exhale 8s",
                fontSize = 16.sp,
                color = TextGrey
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = onSmoke,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
//                    .fillMaxWidth(200.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NetflixGrey)
            ) {
                Text("I smoked", fontSize = 16.sp, color = TextWhite)
            }
        }
    }
}
