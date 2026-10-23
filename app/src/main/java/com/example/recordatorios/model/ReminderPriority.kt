package com.example.recordatorios.model

sealed class ReminderPriority(val label: String) {
    object Low : ReminderPriority("Baja")
    object Medium : ReminderPriority("Media")
    object High : ReminderPriority("Alta")
}