package gs.calendar.appointments.frontend

import material_ui.core.*
import react.RBuilder
import react.dom.div
import react.dom.jsStyle

fun RBuilder.app() {
    appBar {
        attrs {
            position = AppBarPosition.STATIC
        }
        toolBar {
            typography(TypographyVariant.H6) {
                +BuildConfig.APP_NAME
            }
        }
    }
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
        scheduler {}
    }
}
