package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val toolBar = module.Toolbar.unsafeCast<Toolbar>()

fun RBuilder.toolBar(handler: (RHandler<RProps>) = {}) =
    toolBar.invoke(handler)

abstract external class Toolbar : RClass<RProps>
