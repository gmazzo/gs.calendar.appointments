package gs.calendar.appointments.frontend.scheduler

import allOf
import css
import dialogTitleWithActions
import gs.calendar.appointments.frontend.API
import gs.calendar.appointments.frontend.redux.RefreshSlots
import gs.calendar.appointments.frontend.redux.SelectSlot
import gs.calendar.appointments.frontend.redux.dispatch
import gs.calendar.appointments.frontend.redux.uiLinked
import gs.calendar.appointments.frontend.userAvatar
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.SlotParams
import gs.calendar.appointments.model.User
import gs.calendar.appointments.model.params
import kotlinx.css.px
import material_ui.core.ButtonColor
import material_ui.core.ButtonVariant
import material_ui.core.ChipColor
import material_ui.core.TypographyColor
import material_ui.core.TypographyVariant
import material_ui.core.button
import material_ui.core.chip
import material_ui.core.dialog
import material_ui.core.dialogActions
import material_ui.core.dialogContent
import material_ui.core.dialogContentText
import material_ui.core.iconButton
import material_ui.core.styles.WithTheme
import material_ui.core.styles.withTheme
import material_ui.core.textField
import material_ui.core.typography
import material_ui.icons.Icons
import material_ui.icons.icon
import notistack.SnackbarVariant
import notistack.WithSnackbar
import notistack.enqueueSnackbar
import notistack.withSnackbar
import onClick
import org.w3c.dom.events.MouseEvent
import rClass
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import react.dom.div
import react.setState
import kotlin.browser.window
import kotlin.js.Promise

class AppointmentDetails : RComponent<AppointmentDetails.Props, AppointmentDetails.State>() {

    @Suppress("UNUSED_PARAMETER")
    private fun close(ev: MouseEvent? = null) {
        setState {
            editParams = null
            capacityError = null
        }

        SelectSlot(null).dispatch()
    }

    private fun Promise<Slot>.opThen(successMessage: (Slot) -> String) = uiLinked(props)
        .then { props.enqueueSnackbar(message = successMessage(it), variant = SnackbarVariant.SUCCESS) }
        .then { RefreshSlots.dispatch() }

    override fun RBuilder.render() {
        val hasDescription = !props.slot.description.isNullOrBlank()
        val hasAttendees = props.slot.attendees.isNotEmpty()
        val editParams = state.editParams
        val adminMode = editParams != null

        dialog(onClose = ::close) {
            dialogTitleWithActions(title = props.slot.name) {
                if (props.agenda.canChangSlots) {
                    iconButton(color = (ButtonColor.SECONDARY).takeIf { adminMode }) {
                        icon(Icons.SETTINGS)
                        onClick { setState { this.editParams = props.slot.params.takeUnless { adminMode } } }
                    }
                }
                iconButton {
                    icon(Icons.CLOSE)
                    onClick(::close)
                }
            }

            if (hasDescription || hasAttendees || adminMode) {
                dialogContent {
                    if (adminMode) {
                        textField(
                            label = "Capacity",
                            value = editParams!!.capacity.toString(),
                            type = "number",
                            error = state.capacityError != null,
                            onChange = {
                                val value = it.target.asDynamic().value.unsafeCast<String>().toIntOrNull()

                                setState {
                                    this.editParams = editParams.copy(capacity = value ?: 1)
                                    this.capacityError = "Capacity must be at least 1".takeIf { value ?: 1 < 1 }
                                }
                            }
                        )
                        state.capacityError?.let {
                            typography(
                                variant = TypographyVariant.CAPTION,
                                color = TypographyColor.ERROR
                            ) { +it }
                        }

                    } else {
                        dialogContentText(props.slot.description!!)

                        if (hasAttendees) {
                            div {
                                if (hasDescription) {
                                    css { marginTop = (24 - props.theme.spacing.unit).px }
                                }

                                props.slot.attendees.forEach { attendee ->
                                    val self = attendee.isSelf(props.user)

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
                                onClick { window.open(props.slot.externalUrl) }
                            }

                            fun saveButton(label: String, all: Boolean) {
                                button(
                                    label = label,
                                    color = if (all) ButtonColor.SECONDARY else ButtonColor.PRIMARY,
                                    variant = ButtonVariant.CONTAINED
                                ) {
                                    onClick {
                                        API.slotsUpdate(props.agenda.id, props.slot.id, editParams!!, all, props.user)
                                            .opThen {
                                                "Updated ${it.name} at ${it.startTime.toLocaleString()}"
                                            }
                                    }
                                }
                            }

                            if (props.slot.recurrent) {
                                saveButton("Update This", false)
                                saveButton("Update All", true)

                            } else {
                                saveButton("Update", false)
                            }
                        }
                        props.slot.available -> button(
                            label = "Book",
                            color = ButtonColor.PRIMARY,
                            variant = ButtonVariant.CONTAINED
                        ) {
                            onClick {
                                API.slotsBook(props.agenda.id, props.slot.id, props.user!!).opThen {
                                    "Booked ${it.name} at ${it.startTime.toLocaleString()}"
                                }
                            }
                        }
                        props.slot.selfIsAttendee -> button(
                            label = "Cancel",
                            color = ButtonColor.SECONDARY,
                            variant = ButtonVariant.CONTAINED
                        ) {
                            onClick {
                                API.slotsUnbook(props.agenda.id, props.slot.id, props.user!!).opThen {
                                    "Canceled ${it.name} at ${it.startTime.toLocaleString()}"
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    interface Props : WithTheme, WithSnackbar {
        var agenda: Agenda
        var slot: Slot
        var user: User?
    }

    data class State(
        var editParams: SlotParams?,
        var capacityError: String?
    ) : RState

}

private val wrapped = allOf<AppointmentDetails.Props>(withTheme(), withSnackbar())(AppointmentDetails::class.rClass)

fun RBuilder.appointmentDetails(
    agenda: Agenda,
    slot: Slot,
    user: User?,
    handler: (RHandler<AppointmentDetails.Props>) = {}
) = wrapped {
    attrs.agenda = agenda
    attrs.slot = slot
    attrs.user = user

    handler(this)
}
