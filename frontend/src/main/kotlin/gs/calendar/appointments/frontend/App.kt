package gs.calendar.appointments.frontend

import react.RBuilder
import react.dom.div
import react.dom.jsStyle

fun RBuilder.app(handler: RBuilder.() -> Unit) {
    header()
    div("content") {
        attrs {
            jsStyle {
                display = "flex"
                flexDirection = "column"
                flex = 1
                padding = "20px"
                overflow = "auto"
            }
        }
        scheduler()
    }
    handler(this)
}
