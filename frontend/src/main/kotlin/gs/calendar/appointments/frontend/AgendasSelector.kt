package gs.calendar.appointments.frontend

import gs.calendar.appointments.model.Agenda
import jsStyle
import material_ui.core.ButtonColor
import material_ui.core.ButtonVariant
import material_ui.core.color
import material_ui.core.uiButton
import material_ui.core.variant
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RProps
import react.RState
import react.dom.WithClassName
import react.setState

class AgendasSelector : RComponent<WithClassName, AgendasSelector.State>() {

    override fun componentDidMount() {
        API.listAgendas().then {
            setState {
                agendas = it.data?.toList()
                selectedAgenda = agendas?.first()
            }
        }
    }

    override fun RBuilder.render() {
        state.agendas?.forEach {
            val selected = it.id == state.selectedAgenda?.id

            uiButton {
                jsStyle {
                    margin = 8
                }
                attrs {
                    color = if (selected) ButtonColor.INHERIT else ButtonColor.SECONDARY
                    variant = if (selected) ButtonVariant.OUTLINED else ButtonVariant.CONTAINED
                    onClick = { _ -> setState { selectedAgenda = it } }
                }
                +it.name
            }
        }
    }

    data class State(
        var selectedAgenda: Agenda? = null,
        var agendas: List<Agenda>? = null
    ) : RState

}

fun RBuilder.agendasSelector(handler: RHandler<RProps>) =
    child(AgendasSelector::class, handler)
