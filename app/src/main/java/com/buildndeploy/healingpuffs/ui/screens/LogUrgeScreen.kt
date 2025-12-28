package com.buildndeploy.healingpuffs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buildndeploy.healingpuffs.ui.theme.*

@Composable
fun LogUrgeScreen(
    onStartDelay: () -> Unit,
    onBack: () -> Unit
) {
    var intensity by remember { mutableIntStateOf(3) }
    var selectedTriggers by remember { mutableStateOf(setOf("Stress")) }

    val triggers = listOf("Stress", "Boredom", "Habit", "Social")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NetflixBlack)
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Text("←", fontSize = 24.sp, color = TextWhite)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "How strong is it? (1-5)",
            fontSize = 20.sp,
            color = TextWhite
        )

        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = intensity.toFloat(),
            onValueChange = { intensity = it.toInt() },
            valueRange = 1f..5f,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text("What triggered it?", fontSize = 20.sp, color = TextWhite)

        Spacer(modifier = Modifier.height(16.dp))

        TriggerChips(triggers, selectedTriggers) { trigger ->
            selectedTriggers = if (selectedTriggers.contains(trigger)) {
                selectedTriggers - trigger
            } else {
                selectedTriggers + trigger
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onStartDelay,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = NetflixRed)
        ) {
            Text("Start 10-min delay", fontSize = 16.sp)
        }
    }
}

@Composable
private fun TriggerChips(
    triggers: List<String>,
    selectedTriggers: Set<String>,
    onTriggerSelected: (String) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(triggers.size) { index ->
            val trigger = triggers[index]
            FilterChip(
                selected = selectedTriggers.contains(trigger),
                onClick = { onTriggerSelected(trigger) },
                label = { Text(trigger) },
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
