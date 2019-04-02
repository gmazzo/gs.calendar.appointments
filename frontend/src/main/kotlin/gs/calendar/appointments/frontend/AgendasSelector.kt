package gs.calendar.appointments.frontend

import css
import gs.calendar.appointments.frontend.redux.ChangeAgenda
import gs.calendar.appointments.frontend.redux.store
import gs.calendar.appointments.model.Agenda
import kotlinx.css.margin
import kotlinx.css.px
import material_ui.core.ButtonColor
import material_ui.core.TooltipPlacement
import material_ui.core.button
import material_ui.core.menu
import material_ui.core.menuItem
import material_ui.core.tooltip
import notistack.WithSnackbar
import notistack.enqueueSnackbar
import notistack.withSnackbar
import onClick
import org.w3c.dom.Element
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import react.setState

class AgendasSelector : RComponent<AgendasSelector.Props, AgendasSelector.State>() {

    override fun RBuilder.render() {
        props.options?.takeIf { it.isNotEmpty() }?.also { agendas ->
            button(
                color = ButtonColor.SECONDARY,
                label = props.value?.name ?: ""
            ) {
                css {
                    margin(left = 8.px)
                }

                onClick { ev ->
                    val target = ev.target as Element

                    setState { menuAnchor = target }
                    props.enqueueSnackbar("Snackbar!!!")
                }
            }
            menu {
                attrs.open = state.menuAnchor != null
                attrs.anchorEl = state.menuAnchor
                attrs.onClose = { setState { menuAnchor = null } }

                agendas.forEach { agenda ->
                    tooltip(
                        title = agenda.description ?: agenda.name,
                        placement = TooltipPlacement.LEFT
                    ) {
                        menuItem {
                            attrs.disabled = agenda.id == props.value?.id

                            onClick {
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

    interface Props : WithSnackbar {
        var value: Agenda?
        var options: List<Agenda>?
    }

    data class State(
        var menuAnchor: Element?
    ) : RState

}

fun RBuilder.agendasSelector(
    value: Agenda?,
    options: List<Agenda>?,
    handler: (RHandler<AgendasSelector.Props>) = {}
) = child(withSnackbar(AgendasSelector::class)) {
    attrs.value = value
    attrs.options = options

    handler(this)
}
