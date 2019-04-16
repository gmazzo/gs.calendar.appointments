package gs.calendar.appointments.frontend.scheduler

import css
import dialogTitleWithActions
import gs.calendar.appointments.frontend.API
import gs.calendar.appointments.frontend.redux.RefreshSlots
import gs.calendar.appointments.frontend.redux.SelectSlot
import gs.calendar.appointments.frontend.redux.SetAdminMode
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
import material_ui.core.ButtonVariant
import material_ui.core.ChipColor
import material_ui.core.button
import material_ui.core.chip
import material_ui.core.dialog
import material_ui.core.dialogActions
import material_ui.core.dialogContent
import material_ui.core.dialogContentText
import material_ui.core.iconButton
import material_ui.core.styles.WithTheme
import material_ui.icons.Icons
import material_ui.icons.icon
import notistack.SnackbarVariant
import notistack.WithSnackbar
import notistack.enqueueSnackbar
import onClick
import react.RBuilder
import react.dom.div
import kotlin.browser.window
import kotlin.js.Promise

fun <P> RBuilder.appointmentDetails(adminMode: Boolean, agenda: Agenda, slot: Slot, user: User?, props: P)
        where P : WithSnackbar, P : WithTheme {

    fun close() {
        if (adminMode) {
            SetAdminMode(false).dispatch()
        }
        SelectSlot(null).dispatch()
    }

    fun performBook(
        bookOp: (agendaId: AgendaId, slotId: SlotId, user: User, authUser: User?) -> Promise<Slot>,
        successPrefix: String
    ) {
        bookOp(agenda.id, slot.id, user!!, user)
            .uiLinked(props)
            .then { RefreshSlots.dispatch() }
            .then {
                props.enqueueSnackbar(
                    "$successPrefix ${slot.name} at ${slot.startTime.toLocaleString()}",
                    variant = SnackbarVariant.SUCCESS
                )
            }
    }

    dialog(onClose = { close() }) {
        dialogTitleWithActions(title = slot.name) {
            if (agenda.canChangSlots) {
                iconButton {
                    icon(Icons.SETTINGS)
                    onClick { SetAdminMode(!adminMode).dispatch() }
                }
            }
            iconButton {
                icon(Icons.CLOSE)
                onClick { close() }
            }
        }

        val hasDescription = !slot.description.isNullOrBlank()
        val hasAttendees = slot.attendees.isNotEmpty()

        if (adminMode || hasDescription || hasAttendees) {
            dialogContent {
                if (adminMode) {
                    // TODO implement a callback and save the data
                    appointmentEditor(slot)

                } else {
                    dialogContentText(slot.description!!)

                    if (hasAttendees) {
                        div {
                            if (hasDescription) {
                                css { marginTop = (24 - props.theme.spacing.unit).px }
                            }

                            slot.attendees.forEach { attendee ->
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
            }
            dialogActions {
                css {
                    minWidth = 300.px
                    borderTop = "1px solid ${props.theme.palette.divider}"
                    paddingTop = props.theme.spacing.unit.px
                }

                when {
                    adminMode -> {
                        button(
                            label = "Open at Google",
                            color = ButtonColor.DEFAULT
                        ) {
                            onClick { window.open(slot.externalUrl) }
                        }
                        button(
                            label = "Save",
                            color = ButtonColor.PRIMARY,
                            variant = ButtonVariant.CONTAINED
                        ) {
                            // TODO onClick { performSave }
                        }
                    }
                    slot.available -> button(
                        label = "Book",
                        color = ButtonColor.PRIMARY,
                        variant = ButtonVariant.CONTAINED
                    ) {
                        onClick { performBook(API::slotsBook, "Booked") }
                    }
                    slot.selfIsAttendee -> button(
                        label = "Cancel",
                        color = ButtonColor.SECONDARY,
                        variant = ButtonVariant.CONTAINED
                    ) {
                        onClick { performBook(API::slotsUnbook, "Canceled") }
                    }
                }
            }
        }
    }
}
