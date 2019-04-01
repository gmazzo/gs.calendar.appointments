package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val cssBaseline = module.CssBaseline.unsafeCast<RClass<RProps>>()

fun RBuilder.cssBaseline(handler: (RHandler<RProps>) = {}) =
    cssBaseline.invoke(handler)
