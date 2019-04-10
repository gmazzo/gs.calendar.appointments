package gs.calendar.appointments.frontend.scheduler

import css
import gs.calendar.appointments.frontend.API
import gs.calendar.appointments.frontend.redux.RefreshSlot
import gs.calendar.appointments.frontend.redux.SelectSlot
import gs.calendar.appointments.frontend.redux.dispatch
import gs.calendar.appointments.frontend.redux.uiLinked
import gs.calendar.appointments.frontend.userAvatar
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.User
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.css.px
import material_ui.core.ButtonColor
import material_ui.core.ChipColor
import material_ui.core.button
import material_ui.core.chip
import material_ui.core.dialog
import material_ui.core.dialogActions
import material_ui.core.dialogContent
import material_ui.core.dialogContentText
import material_ui.core.dialogTitle
import material_ui.core.styles.WithTheme
import notistack.WithSnackbar
import onClick
import react.RBuilder
import react.dom.div
import kotlin.js.Promise

fun <P> RBuilder.appointmentDetails(agenda: Agenda?, slot: Slot?, user: User?, props: P)
        where P : WithSnackbar, P : WithTheme {
    if (agenda != null && slot != null) {

        fun performBook(bookOp: (agendaId: AgendaId, slotId: SlotId, user: User) -> Promise<Slot>) {
            bookOp(agenda.id, slot.id, user!!)
                .uiLinked(props)
                .then { RefreshSlot(it).dispatch() } // reloads the agenda
        }

        dialog(onClose = { SelectSlot(null).dispatch() }) {
            dialogTitle(slot.name)
            dialogContent {
                css {
                    minWidth = 300.px
                    padding(0.px)
                }

                slot.description?.let { dialogContentText(it) }

                slot.attendees.takeIf { it.isNotEmpty() }?.let {
                    div {
                        it.forEach { attendee ->
                            val self = attendee.isSelf(user)

                            chip(
                                color = if (self) ChipColor.PRIMARY else null,
                                avatar = { userAvatar(attendee) },
                                label = (attendee.name ?: attendee.email).let { if (self) "$it (you)" else it }
                            ) { css { margin(props.theme.spacing.unit.px) } }
                        }
                    }
                }

                dialogActions {
                    if (slot.availableFor(user)) {
                        button(label = "Book", color = ButtonColor.PRIMARY) {
                            onClick { performBook(API::book) }
                        }

                    } else if (user in slot) {
                        button(label = "Unbook", color = ButtonColor.SECONDARY) {
                            onClick { performBook(API::unbook) }
                        }
                    }
                }
            }
        }
    }
}
