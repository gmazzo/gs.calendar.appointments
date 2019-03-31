package gs.calendar.appointments.frontend

import css
import gs.calendar.appointments.frontend.redux.ChangeAgenda
import gs.calendar.appointments.frontend.redux.showLoading
import gs.calendar.appointments.frontend.redux.store
import gs.calendar.appointments.model.Agenda
import kotlinx.css.margin
import kotlinx.css.px
import material_ui.core.ButtonColor
import material_ui.core.TooltipPlacement
import material_ui.core.uiButton
import material_ui.core.uiMenu
import material_ui.core.uiMenuItem
import material_ui.core.uiTooltip
import org.w3c.dom.Element
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RProps
import react.RState
import react.setState

class AgendasSelector : RComponent<AgendasSelector.Props, AgendasSelector.State>() {

    override fun componentDidMount() {
        API.listAgendas()
            .then {
                setState { agendas = it.data?.toList() }

                store.dispatch(ChangeAgenda(it.data?.first()))
            }
            .showLoading()
    }

    override fun RBuilder.render() {
        state.agendas?.takeIf { it.isNotEmpty() }?.also { agendas ->
            uiButton(
                color = ButtonColor.SECONDARY,
                label = props.value?.name ?: ""
            ) {
                css {
                    margin(left = 8.px)
                }

                attrs.onClick = { ev ->
                    val target = ev.target as Element

                    setState { menuAnchor = target }
                }
            }
            uiMenu {
                attrs.open = state.menuAnchor != null
                attrs.anchorEl = state.menuAnchor
                attrs.onClose = { setState { menuAnchor = null } }

                agendas.forEach { agenda ->
                    uiTooltip(
                        title = agenda.description ?: agenda.name,
                        placement = TooltipPlacement.LEFT
                    ) {
                        uiMenuItem {
                            attrs.disabled = agenda.id == props.value?.id
                            attrs.onClick = {
                                setState { menuAnchor = null }

                                store.dispatch(ChangeAgenda(agenda))
                            }

                            +agenda.name
                        }
                    }
                }
            }
        }
    }

    interface Props : RProps {
        var value: Agenda?
    }

    data class State(
        var menuAnchor: Element?,
        var agendas: List<Agenda>? = null
    ) : RState

}

fun RBuilder.agendasSelector(
    value: Agenda?,
    handler: (RHandler<AgendasSelector.Props>) = {}
) = child(AgendasSelector::class) {
    attrs.value = value
    handler(this)
}
