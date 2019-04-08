package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val listItemAvatar = module.ListItemAvatar.unsafeCast<ListItemAvatar>()

fun RBuilder.listItemAvatar(
    handler: (RHandler<RProps>) = {}
) = listItemAvatar.invoke(handler)

abstract external class ListItemAvatar : RClass<RProps>
