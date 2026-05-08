package com.example.a211126_nelson_project1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.a211126_nelson_project1.ui.theme.BebasNeue
import com.example.a211126_nelson_project1.ui.theme.BricolageGrotesque

data class AppNotification(
    val title: String,
    val message: String
)

val notificationList = listOf(
    AppNotification("New Product", "Bamboo Toothbrush just added!"),
    AppNotification("Discount", "Reusable Straw Set 20% OFF"),
    AppNotification("Eco Tip", "Use reusable bags to reduce plastic"),
    AppNotification("Reminder", "Check your favorite items today")
)

@Composable
fun NotiScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "NOTIFICATIONS",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontFamily = BebasNeue
        )

        Spacer(modifier = Modifier.height(12.dp))

        Divider(
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (notificationList.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No notifications yet",
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = BricolageGrotesque
                )
            }

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(notificationList) { notif ->
                    NotificationItem(notif)
                }
            }
        }
    }
}

@Composable
fun NotificationItem(notification: AppNotification) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.large
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = BricolageGrotesque,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = BricolageGrotesque,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}