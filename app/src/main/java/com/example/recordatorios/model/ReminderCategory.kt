package com.example.recordatorios.model

sealed class ReminderCategory(val label: String) {
    object Work : ReminderCategory("Trabajo")
    object Personal : ReminderCategory("Personal")
    object Study : ReminderCategory("Estudio")
    object Health : ReminderCategory("Salud")
}