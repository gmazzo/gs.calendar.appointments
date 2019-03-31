package gs.calendar.appointments.frontend.redux

import redux.RAction
import redux.createStore
import redux.rEnhancer

val appStore = createStore(::myReducer, AppState(), rEnhancer())

private fun myReducer(state: AppState, action: RAction) = when (action) {
    is Action -> action(state)
    else -> state
}
