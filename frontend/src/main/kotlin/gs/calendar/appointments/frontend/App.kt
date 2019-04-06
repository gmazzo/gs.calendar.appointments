package gs.calendar.appointments.frontend

import allOf
import css
import gs.calendar.appointments.frontend.header.header
import gs.calendar.appointments.frontend.redux.SetAgendas
import gs.calendar.appointments.frontend.redux.store
import gs.calendar.appointments.frontend.redux.uiLinked
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.Slot
import gs.calendar.appointments.model.User
import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.Overflow
import kotlinx.css.padding
import kotlinx.css.px
import material_ui.core.styles.WithTheme
import material_ui.core.styles.withTheme
import notistack.WithSnackbar
import notistack.withSnackbar
import rClass
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import react.dom.div
import redux.state

class App : RComponent<App.Props, App.State>() {

    private lateinit var unsubscribe: () -> Unit

    override fun componentDidMount() {
        unsubscribe = store.subscribe { setState(store.state) }

        API.listAgendas()
            .uiLinked(props)
            .then { store.dispatch(SetAgendas(it.toList())) }
    }

    override fun componentWillUnmount() {
        unsubscribe()
    }

    override fun RBuilder.render() {
        header(
            agendas = state.agendas,
            currentAgenda = state.currentAgenda,
            loading = state.loadingCount > 0
        )
        div("content") {
            css {
                padding(props.theme.spacing.unit.px * 4)
                display = Display.flex
                flexDirection = FlexDirection.column
                flexGrow = 1.0
                overflow = Overflow.auto
            }
            scheduler(agenda = state.currentAgenda)
        }
        loginDialog(
            currentUser = state.currentUser,
            withSnackbar = props
        )
        appointmentDetails(
            agenda = state.currentAgenda,
            slot = state.currentSlot,
            user = state.currentUser,
            withSnackbar = props
        )
    }

    interface Props : WithTheme, WithSnackbar

    data class State(
        val loadingCount: Int = 0,
        val agendas: List<Agenda>? = null,
        val currentUser: User? = null,
        val currentAgenda: Agenda? = null,
        val currentSlot: Slot? = null
    ) : RState

}

private val wrapped = allOf<App.Props>(withTheme(), withSnackbar())(App::class.rClass)

fun RBuilder.app(handler: (RHandler<App.Props>) = {}) = wrapped(handler)
