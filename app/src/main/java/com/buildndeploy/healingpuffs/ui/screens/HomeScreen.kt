package com.buildndeploy.healingpuffs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buildndeploy.healingpuffs.ui.theme.NetflixBlack
import com.buildndeploy.healingpuffs.ui.theme.NetflixDarkGrey
import com.buildndeploy.healingpuffs.ui.theme.TextGrey
import com.buildndeploy.healingpuffs.ui.theme.TextWhite


@Composable
fun HomeScreen(
    lastUrge: String?,
    lastSmoke: String?,
    onLogUrge: () -> Unit,
    onLogSmoke: () -> Unit,
    onPatterns: () -> Unit,
    onSettings: () -> Unit  // Added settings callback
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NetflixBlack)
            .padding(16.dp)
    ) {
        // Status Bar
        StatusBar(lastUrge = lastUrge, lastSmoke = lastSmoke)

        Spacer(modifier = Modifier.height(32.dp))

        // Action Cards (Netflix style)
        ActionCard(
            title = "Log an urge",
            subtitle = "Catch it before it happens",
            icon = "📍",
            onClick = onLogUrge
        )

        Spacer(modifier = Modifier.height(24.dp))

        ActionCard(
            title = "I smoked",
            subtitle = "Noted. No judgment.",
            icon = "🚬",
            onClick = onLogSmoke
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Bottom Navigation
        BottomNavigationBar(
            onPatterns = onPatterns,
            onSettings = onSettings  // Pass settings callback
        )
    }
}

@Composable
private fun StatusBar(lastUrge: String?, lastSmoke: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = NetflixDarkGrey),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Last urge: ${lastUrge ?: "Never"}",
                color = TextGrey,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Last smoke: ${lastSmoke ?: "Never"}",
                color = TextGrey,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun ActionCard(
    title: String,
    subtitle: String,
    icon: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = NetflixDarkGrey)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                fontSize = 32.sp,
                modifier = Modifier.padding(end = 16.dp)
            )
            Column {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = TextGrey
                )
            }
        }
    }
}


@Composable
private fun BottomNavigationBar(
    onPatterns: () -> Unit,
    onSettings: () -> Unit  // ADD THIS
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavigationItem("Home", "🏠", selected = true)
        NavigationItem("Patterns", "📊", selected = false, onClick = onPatterns)
        NavigationItem("Settings", "⚙️", selected = false, onClick = onSettings)  // FIX THIS
    }
}



@Composable
private fun NavigationItem(
    title: String,
    icon: String,
    selected: Boolean,
    onClick: (() -> Unit)? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(enabled = onClick != null) {
            onClick?.invoke()
        }
    ) {
        Text(
            text = icon,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            color = if (selected) TextWhite else TextGrey
        )
    }
}
