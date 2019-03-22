package gs.calendar.appointments.frontend

import material_ui.core.Position
import material_ui.core.appBar
import react.RBuilder
import react.dom.div
import react.dom.h6

fun RBuilder.app() {
    appBar {
        attrs {
            className = "appBar"
            position = Position.FIXED.value
        }
        h6 {
            +BuildConfig.APP_NAME
        }
    }
    div("content") {
        scheduler()
    }
}
