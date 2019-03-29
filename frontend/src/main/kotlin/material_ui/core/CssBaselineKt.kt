package material_ui.core

import react.RBuilder
import react.RHandler
import react.RProps

fun RBuilder.uiCssBaseline(handler: RHandler<RProps>) = cssBaseline.invoke(handler)
