package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val list = module.List.unsafeCast<List>()

fun RBuilder.list(handler: (RHandler<RProps>) = {}) =
    list.invoke(handler)

abstract external class List : RClass<RProps>