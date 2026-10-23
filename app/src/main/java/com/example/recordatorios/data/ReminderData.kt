package com.example.recordatorios.data

import com.example.recordatorios.model.Reminder
import com.example.recordatorios.model.ReminderCategory
import com.example.recordatorios.model.ReminderPriority
import com.example.recordatorios.model.ReminderStatus

object ReminderData {

    val reminders: List<Reminder> = listOf(
        Reminder(
            1,
            "Entregar actividad práctica",
            "Completar la aplicación de recordatorios",
            ReminderCategory.Study,
            ReminderPriority.High,
            ReminderStatus.Pending
        ),
        Reminder(
            2,
            "Repasar Kotlin",
            "Revisar clases y listas",
            ReminderCategory.Study,
            ReminderPriority.Medium,
            ReminderStatus.Pending
        ),
        Reminder(
            3,
            "Llamar al médico",
            "Solicitar un turno",
            ReminderCategory.Health,
            ReminderPriority.High,
            ReminderStatus.Pending
        ),
        Reminder(
            4,
            "Comprar alimentos",
            "Pasar por el supermercado",
            ReminderCategory.Personal,
            ReminderPriority.Low,
            ReminderStatus.Completed
        ),
        Reminder(
            5,
            "Enviar informe",
            "Compartir el informe semanal",
            ReminderCategory.Work,
            ReminderPriority.Medium,
            ReminderStatus.Completed
        )
    )

    val uniqueCategories: Set<ReminderCategory> =
        reminders.map { it.category }.toSet()

    val categoryLabels: Map<ReminderCategory, String> = mapOf(
        ReminderCategory.Work to "Trabajo",
        ReminderCategory.Personal to "Personal",
        ReminderCategory.Study to "Estudio",
        ReminderCategory.Health to "Salud"
    )

    val categoryColors: Map<ReminderCategory, Long> = mapOf(
        ReminderCategory.Work to 0xFF6750A4L,
        ReminderCategory.Personal to 0xFF006A60L,
        ReminderCategory.Study to 0xFF386A20L,
        ReminderCategory.Health to 0xFFBA1A1AL
    )

    fun filterByStatus(status: ReminderStatus): List<Reminder> =
        reminders.filter { it.status == status }
}