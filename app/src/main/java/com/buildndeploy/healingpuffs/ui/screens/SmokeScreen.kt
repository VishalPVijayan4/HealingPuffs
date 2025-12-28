package com.buildndeploy.healingpuffs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buildndeploy.healingpuffs.ui.theme.NetflixBlack
import com.buildndeploy.healingpuffs.ui.theme.NetflixGrey
import com.buildndeploy.healingpuffs.ui.theme.NetflixRed
import com.buildndeploy.healingpuffs.ui.theme.TextGrey
import com.buildndeploy.healingpuffs.ui.theme.TextWhite

@Composable
fun SmokeScreen(
    onContinue: () -> Unit,
    onBack: () -> Unit
) {
    var selectedTrigger by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NetflixBlack)
            .padding(16.dp)
    ) {
        Text(
            text = "Noted.",
            fontSize = 32.sp,
            color = TextWhite
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "What led to this?",
            fontSize = 20.sp,
            color = TextGrey
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listOf("Stress", "Boredom", "Habit", "Social")) { trigger ->
                TriggerChip(
                    text = trigger,
                    selected = selectedTrigger == trigger,
                    onClick = { selectedTrigger = trigger }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = NetflixRed)
        ) {
            Text("Prepare for next time")
        }
    }
}

@Composable
private fun TriggerChip(text: String, selected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .then(
                if (selected) Modifier.background(NetflixRed) else Modifier
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) NetflixRed else NetflixGrey
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            color = if (selected) TextWhite else TextGrey
        )
    }
}
