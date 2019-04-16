package gs.calendar.appointments.frontend.scheduler

import gs.calendar.appointments.model.Slot
import material_ui.core.TypographyColor
import material_ui.core.TypographyVariant
import material_ui.core.textField
import material_ui.core.typography
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RProps
import react.RState
import react.setState

class AppointmentEditor(props: Props) : RComponent<AppointmentEditor.Props, AppointmentEditor.State>(props) {

    override fun State.init(props: Props) {
        capacity = props.slot.capacity
    }

    override fun RBuilder.render() {
        textField(
            label = "Capacity",
            value = state.capacity?.toString(),
            fullWidth = true,
            type = "number",
            error = state.capacityError != null,
            onChange = {
                val value = it.target.asDynamic().value.unsafeCast<String>().toIntOrNull()

                setState {
                    capacity = value
                    capacityError = "Capacity must be at least 1".takeIf { value ?: 1 < 1 }
                }
            }
        )
        state.capacityError?.let {
            typography(
                variant = TypographyVariant.CAPTION,
                color = TypographyColor.ERROR
            ) { +it }
        }
    }

    interface Props : RProps {
        var slot: Slot
    }

    data class State(
        var capacity: Int?,
        var capacityError: String?
    ) : RState

}

fun RBuilder.appointmentEditor(
    slot: Slot,
    handler: (RHandler<AppointmentEditor.Props>) = {}
) = child(AppointmentEditor::class) {
    attrs.slot = slot

    handler(this)
}
