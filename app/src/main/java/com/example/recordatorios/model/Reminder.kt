package com.example.recordatorios.model

data class Reminder(
    val id: Int,
    val title: String,
    val description: String,
    val category: ReminderCategory,
    val priority: ReminderPriority,
    val status: ReminderStatus
) {
    init {
        require(id > 0) { "El identificador debe ser mayor que cero" }
        require(title.isNotBlank()) { "El título no puede estar vacío" }
    }
}