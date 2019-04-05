package gs.calendar.appointments.frontend

import gs.calendar.appointments.model.Slot
import material_ui.core.ButtonColor
import material_ui.core.button
import material_ui.core.dialog
import material_ui.core.dialogActions
import material_ui.core.dialogContent
import material_ui.core.dialogContentText
import material_ui.core.dialogTitle
import react.RBuilder

fun RBuilder.appointment(slot: Slot?) {
    if (slot != null) {
        dialog {
            dialogTitle(slot.description)
            dialogContent {
                slot.extraInfo?.let { dialogContentText(it) }
                dialogActions {
                    button(label = "Book", color = ButtonColor.PRIMARY)
                }
            }
        }
    }
}
