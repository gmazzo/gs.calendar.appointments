package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.redux.SelectAgenda
import gs.calendar.appointments.frontend.redux.SelectSlot
import gs.calendar.appointments.frontend.redux.store
import gs.calendar.appointments.frontend.redux.uiLinked
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.User
import material_ui.core.ButtonColor
import material_ui.core.button
import material_ui.core.dialog
import material_ui.core.dialogActions
import material_ui.core.dialogContent
import material_ui.core.dialogContentText
import material_ui.core.dialogTitle
import notistack.WithSnackbar
import onClick
import react.RBuilder

fun RBuilder.appointmentDetails(agenda: Agenda?, slot: Slot?, user: User?, withSnackbar: WithSnackbar) {
    if (agenda != null && slot != null) {
        dialog(onClose = { store.dispatch(SelectSlot(null)) }) {
            dialogTitle(slot.description)
            dialogContent {
                slot.extraInfo?.let { dialogContentText(it) }
                dialogActions {
                    if (user != null) {
                        button(label = "Book", color = ButtonColor.PRIMARY) {
                            onClick {
                                API.book(agenda.id, slot.id, user)
                                    .uiLinked(withSnackbar)
                                    .then { store.dispatch(SelectAgenda(agenda)) } // reloads the agenda
                            }
                        }
                    }
                }
            }
        }
    }
}
