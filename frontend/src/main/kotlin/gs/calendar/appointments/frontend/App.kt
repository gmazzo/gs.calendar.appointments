package gs.calendar.appointments.frontend

import material_ui.core.*
import react.RBuilder
import react.dom.div

fun RBuilder.app() {
    appBar {
        attrs {
            className = "appBar"
            position = AppBarPosition.FIXED
        }
        toolBar {
            typography(TypographyVariant.H6) {
                +BuildConfig.APP_NAME
            }
        }
    }
    div("content") {
        scheduler()
    }
}
