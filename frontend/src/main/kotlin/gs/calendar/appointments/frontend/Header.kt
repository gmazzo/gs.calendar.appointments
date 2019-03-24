package gs.calendar.appointments.frontend

import jsStyle
import material_ui.core.AppBarPosition
import material_ui.core.TypographyVariant
import material_ui.core.position
import material_ui.core.uiAppBar
import material_ui.core.uiToolBar
import material_ui.core.uiTypography
import react.RBuilder

fun RBuilder.header() {
    uiAppBar {
        attrs {
            position = AppBarPosition.STATIC
        }
        uiToolBar {
            uiTypography(TypographyVariant.H6) {
                jsStyle {
                    flex = 1
                }
                +BuildConfig.APP_NAME
            }
            agendasSelector { }
        }
    }
}
