package gs.calendar.appointments.frontend

import gs.calendar.appointments.model.Agenda
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.option
import react.dom.select

class AgendasSelector : RComponent<RProps, AgendasSelector.State>() {

    override fun componentDidMount() {
        API.listAgendas().then {
            setState(State(agendas = it.data?.toList()))
        }
    }

    override fun RBuilder.render() {
        select {
            state.agendas?.forEach {
                option {
                    attrs { value = it.id }
                    +(it.description ?: it.id)
                }
            }
        }
    }

    data class State(
        var agendas: List<Agenda>? = null
    ) : RState

}

fun RBuilder.agendasSelector() = child(AgendasSelector::class) {}
