package gs.calendar.appointments.frontend

import css
import gs.calendar.appointments.frontend.redux.store
import gs.calendar.appointments.model.Agenda
import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.Overflow
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RProps
import react.RState
import react.dom.div
import redux.state

class App : RComponent<RProps, App.State>() {

    private lateinit var unsubscribe: () -> Unit

    override fun componentDidMount() {
        unsubscribe = store.subscribe { setState(store.state) }
    }

    override fun componentWillUnmount() {
        unsubscribe()
    }

    override fun RBuilder.render() {
        header(currentAgenda = state.currentAgenda, loading = state.loadingCount > 0)
        div("content") {
            css {
                padding(20.px)
                display = Display.flex
                flexDirection = FlexDirection.column
                flexGrow = 1.0
                overflow = Overflow.auto
            }

            scheduler(agenda = state.currentAgenda)
        }
    }

    data class State(
        val loadingCount: Int = 0,
        val currentAgenda: Agenda? = null
    ) : RState

}

fun RBuilder.app(handler: (RHandler<RProps>) = {}) = child(App::class, handler)
