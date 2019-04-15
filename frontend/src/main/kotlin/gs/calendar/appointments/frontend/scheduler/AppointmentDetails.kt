package gs.calendar.appointments.frontend.scheduler

import css
import gs.calendar.appointments.frontend.API
import gs.calendar.appointments.frontend.redux.SelectAgenda
import gs.calendar.appointments.frontend.redux.SelectSlot
import gs.calendar.appointments.frontend.redux.dispatch
import gs.calendar.appointments.frontend.redux.uiLinked
import gs.calendar.appointments.frontend.userAvatar
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.AgendaId
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotId
import gs.calendar.appointments.model.User
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
import notistack.SnackbarVariant
import notistack.WithSnackbar
import notistack.enqueueSnackbar
import onClick
import react.RBuilder
import react.dom.div
import kotlin.js.Promise

fun <P> RBuilder.appointmentDetails(agenda: Agenda?, slot: Slot?, user: User?, props: P)
        where P : WithSnackbar, P : WithTheme {
    if (agenda != null && slot != null) {

        fun performBook(
            bookOp: (agendaId: AgendaId, slotId: SlotId, user: User, authUser: User?) -> Promise<Slot>,
            successPrefix: String
        ) {
            bookOp(agenda.id, slot.id, user!!, user)
                .uiLinked(props)
                .then { SelectAgenda(agenda.copy()).dispatch() } // reloads the agenda
                .then {
                    props.enqueueSnackbar(
                        "$successPrefix ${slot.name} at ${slot.startTime.toLocaleString()}",
                        variant = SnackbarVariant.SUCCESS
                    )
                }
        }

        dialog(onClose = { SelectSlot(null).dispatch() }) {
            dialogTitle(slot.name)
            dialogContent {
                css { minWidth = 300.px }

                val hasDescription = !slot.description.isNullOrBlank()
                if (hasDescription) {
                    dialogContentText(slot.description!!)
                }

                slot.attendees.takeIf { it.isNotEmpty() }?.let {
                    div {
                        if (hasDescription) {
                            css { marginTop = (24 - props.theme.spacing.unit).px }
                        }

                        it.forEach { attendee ->
                            val self = attendee.isSelf(user)

                            chip(
                                color = if (self) ChipColor.PRIMARY else null,
                                avatar = { userAvatar(attendee) },
                                label = attendee.name ?: attendee.email
                            ) { css { marginRight = props.theme.spacing.unit.px } }
                        }
                    }
                }
            }

            if (slot.available || slot.selfIsAttendee) {
                dialogActions {
                    css {
                        borderTop = "1px solid ${props.theme.palette.divider}"
                        paddingTop = props.theme.spacing.unit.px
                    }

                    when {
                        slot.available -> button(label = "Book", color = ButtonColor.PRIMARY) {
                            onClick { performBook(API::book, "Booked") }
                        }
                        slot.selfIsAttendee -> button(label = "Cancel", color = ButtonColor.SECONDARY) {
                            onClick { performBook(API::unbook, "Canceled") }
                        }
                    }
                }
            }
        }
    }
}
