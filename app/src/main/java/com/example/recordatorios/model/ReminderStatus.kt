package com.example.recordatorios.model

sealed class ReminderStatus(val label: String) {
    object Pending : ReminderStatus("Pendiente")
    object Completed : ReminderStatus("Completado")
}