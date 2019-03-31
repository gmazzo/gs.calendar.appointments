package gs.calendar.appointments.frontend.redux

import finally
import gs.calendar.appointments.model.Agenda
import redux.RAction
import kotlin.js.Promise

fun Promise<*>.showLoading() = appStore.dispatch(StartLoading(this))

sealed class Action : RAction {

    abstract operator fun invoke(state: AppState): AppState

}

class StartLoading(private val promise: Promise<*>) : Action() {

    override fun invoke(state: AppState) = state.copy(
        loading = true,
        currentPromise = state.currentPromise?.finally { promise.finallyStopLoading() }
            ?: promise.finallyStopLoading()
    )

    private fun Promise<*>.finallyStopLoading() = apply {
        finally { appStore.dispatch(StopLoading(promise)) }
    }

}

class StopLoading(private val promise: Promise<*>) : Action() {

    override fun invoke(state: AppState) = if (promise == state.currentPromise) state.copy(
        loading = false,
        currentPromise = null
    ) else state

}

class ChangeAgenda(private val agenda: Agenda?) : Action() {

    override fun invoke(state: AppState) = state.copy(
        currentAgenda = agenda
    )

}
