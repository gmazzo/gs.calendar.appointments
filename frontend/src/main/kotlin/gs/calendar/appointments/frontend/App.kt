package gs.calendar.appointments.frontend

import gs.calendar.appointments.BuildConfig
import material_ui.core.Position
import material_ui.core.appBar
import react.RBuilder
import react.dom.div

fun RBuilder.app() {
    appBar {
        attrs {
            className = "appBar"
            position = Position.FIXED.value
        }
        +BuildConfig.APP_NAME
    }
    div("content") {
        scheduler()
    }
}
