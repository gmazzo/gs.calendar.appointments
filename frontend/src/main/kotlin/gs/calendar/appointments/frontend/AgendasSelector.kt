package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.redux.ChangeAgenda
import gs.calendar.appointments.frontend.redux.appStore
import gs.calendar.appointments.frontend.redux.showLoading
import gs.calendar.appointments.model.Agenda
import jsStyle
import material_ui.core.ButtonColor
import material_ui.core.TooltipPlacement
import material_ui.core.TypographyVariant
import material_ui.core.uiButton
import material_ui.core.uiMenu
import material_ui.core.uiMenuItem
import material_ui.core.uiTooltip
import material_ui.core.uiTypography
import org.w3c.dom.Element
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RProps
import react.RState
import react.setState
import redux.state

class AgendasSelector : RComponent<RProps, AgendasSelector.State>() {

    private lateinit var unsubscribe: () -> Unit

    override fun componentWillMount() {
        unsubscribe = appStore.subscribe { setState { selectedAgenda = appStore.state.currentAgenda } }
    }

    override fun componentWillUnmount() {
        unsubscribe()
    }

    override fun componentDidMount() {
        API.listAgendas()
            .then {
                setState { agendas = it.data?.toList() }

                appStore.dispatch(ChangeAgenda(it.data?.first()))
            }
            .showLoading()
    }

    override fun RBuilder.render() {
        state.selectedAgenda?.description?.also {
            uiTypography(TypographyVariant.H6) {
                +it
            }
        }
        state.agendas?.takeIf { it.isNotEmpty() }?.also { agendas ->
            uiButton(
                color = ButtonColor.SECONDARY,
                label = state.selectedAgenda?.name ?: ""
            ) {
                jsStyle {
                    marginLeft = 8
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
                            attrs.disabled = agenda.id == state.selectedAgenda?.id
                            attrs.onClick = {
                                setState { menuAnchor = null }

                                appStore.dispatch(ChangeAgenda(agenda))
                            }

                            +agenda.name
                        }
                    }
                }
            }
        }
    }

    data class State(
        var menuAnchor: Element?,
        var selectedAgenda: Agenda? = null,
        var agendas: List<Agenda>? = null
    ) : RState

}

fun RBuilder.agendasSelector(handler: RHandler<RProps>) =
    child(AgendasSelector::class, handler)
