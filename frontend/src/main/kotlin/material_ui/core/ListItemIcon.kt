package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val listItemIcon = module.ListItemIcon.unsafeCast<ListItemIcon>()

fun RBuilder.listItemIcon(
    handler: (RHandler<RProps>) = {}
) = listItemIcon.invoke(handler)

abstract external class ListItemIcon : RClass<RProps>
