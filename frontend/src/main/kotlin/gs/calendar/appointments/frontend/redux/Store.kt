package gs.calendar.appointments.frontend.redux

import gs.calendar.appointments.frontend.App
import redux.RAction
import redux.createStore
import redux.rEnhancer

val store = createStore(::stateReducer, App.State(), rEnhancer())

private fun stateReducer(state: App.State, action: RAction) = when (action) {
    is Action -> action(state)
    else -> state
}.also { console.log("redux:stateReducer", action, "changed=${state !== it}", it) }
