package com.buildndeploy.healingpuffs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buildndeploy.healingpuffs.ui.theme.*

@Composable
fun PatternsScreen(
    patterns: List<String>,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NetflixBlack)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
        ) {
            Text("Your trends", fontSize = 24.sp, color = TextWhite)
        }

        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(patterns) { pattern ->
                PatternCard(pattern)
            }
        }
    }
}

@Composable
private fun PatternCard(pattern: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = NetflixDarkGrey),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = pattern,
            modifier = Modifier.padding(16.dp),
            color = TextWhite
        )
    }
}
