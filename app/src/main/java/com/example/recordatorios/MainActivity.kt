package com.example.recordatorios

import android.os.Bundle
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.recordatorios.data.ReminderData
import com.example.recordatorios.model.Reminder
import com.example.recordatorios.model.ReminderStatus
import com.example.recordatorios.ui.theme.RecordatoriosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecordatoriosTheme {
                ReminderScreen()
            }
        }
    }
}

@Composable
fun ReminderScreen() {
    var showPendingOnly by remember { mutableStateOf(false) }

    val reminders = if (showPendingOnly) {
        ReminderData.filterByStatus(ReminderStatus.Pending)
    } else {
        ReminderData.reminders
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        Text(
            text = "Mis recordatorios",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Organizá tus tareas y actividades",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.padding(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            FilterChip(
                selected = !showPendingOnly,
                onClick = { showPendingOnly = false },
                label = { Text("Todos") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            FilterChip(
                selected = showPendingOnly,
                onClick = { showPendingOnly = true },
                label = { Text("Pendientes") }
            )
        }

        Text(
            text = "${ReminderData.uniqueCategories.size} categorías · ${reminders.size} recordatorios",
            style = MaterialTheme.typography.labelMedium
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(reminders, key = { it.id }) { reminder ->
                ReminderCard(reminder)
            }
        }
    }
}

@Composable
fun ReminderCard(reminder: Reminder) {
    val categoryColor = Color(
        ReminderData.categoryColors[reminder.category] ?: 0xFF6750A4L
    )
    val categoryLabel =
        ReminderData.categoryLabels[reminder.category] ?: reminder.category.label

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = categoryLabel,
                    color = categoryColor,
                    modifier = Modifier
                        .background(
                            color = categoryColor.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Prioridad ${reminder.priority.label.lowercase()}",
                    style = MaterialTheme.typography.labelMedium
                )
            }

            Text(
                text = reminder.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = reminder.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = reminder.status.label,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun ReminderScreenPreview() {
    RecordatoriosTheme {
        ReminderScreen()
    }
}