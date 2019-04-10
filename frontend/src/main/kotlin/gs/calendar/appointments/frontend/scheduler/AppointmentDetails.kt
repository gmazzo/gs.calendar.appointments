package gs.calendar.appointments.frontend.scheduler

import css
import gs.calendar.appointments.frontend.API
import gs.calendar.appointments.frontend.redux.SelectSlot
import gs.calendar.appointments.frontend.redux.dispatch
import gs.calendar.appointments.frontend.redux.uiLinked
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.User
import kotlinx.css.padding
import kotlinx.css.px
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
        dialog(onClose = { SelectSlot(null).dispatch() }) {
            dialogTitle(slot.name)
            dialogContent {
                css {
                    minWidth = 300.px
                    padding(0.px)
                }

                slot.description?.let { dialogContentText(it) }
                dialogActions {
                    if (slot.availableFor(user)) {
                        button(label = "Book", color = ButtonColor.PRIMARY) {
                            onClick {
                                API.book(agenda.id, slot.id, user!!)
                                    .uiLinked(withSnackbar)
                                    .then { SelectSlot(it).dispatch() } // reloads the agenda
                            }
                        }
                    } else if (user in slot) {
                        button(label = "Unbook", color = ButtonColor.SECONDARY) {
                            onClick {
                                API.unbook(agenda.id, slot.id, user!!)
                                    .uiLinked(withSnackbar)
                                    .then { SelectSlot(it).dispatch() } // reloads the agenda
                            }
                        }
                    }
                }
            }
        }
    }
}
