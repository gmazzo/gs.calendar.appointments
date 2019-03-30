package gs.calendar.appointments.frontend

import gs.calendar.appointments.model.Agenda
import jsStyle
import kotlinx.css.px
import material_ui.core.ButtonColor
import material_ui.core.ButtonVariant
import material_ui.core.TooltipPlacement
import material_ui.core.color
import material_ui.core.placement
import material_ui.core.uiButton
import material_ui.core.uiCircularProgress
import material_ui.core.uiTooltip
import material_ui.core.variant
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RProps
import react.RState
import react.setState
import styled.css
import styled.styledDiv

class AgendasSelector : RComponent<AgendasSelector.Props, AgendasSelector.State>() {

    override fun componentDidMount() {
        API.listAgendas().then {
            setState {
                agendas = it.data?.toList()
                selectedAgenda = agendas?.first()
            }
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                minWidth = 250.px
            }
            if (state.agendas == null) {
                uiCircularProgress { }

            } else {
                state.agendas?.forEach {
                    val selected = it.id == state.selectedAgenda?.id

                    uiTooltip {
                        attrs {
                            title = it.description ?: it.name
                            placement = TooltipPlacement.BOTTOM
                        }
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
            }
        }
    }

    interface Props : RProps

    data class State(
        var selectedAgenda: Agenda? = null,
        var agendas: List<Agenda>? = null
    ) : RState

}

fun RBuilder.agendasSelector(handler: RHandler<AgendasSelector.Props>) =
    child(AgendasSelector::class, handler)
