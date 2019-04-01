package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler

private val menuItem = module.MenuItem.unsafeCast<MenuItem>()

fun RBuilder.menuItem(handler: (RHandler<MenuItem.Props>) = {}) =
    menuItem.invoke(handler)

abstract external class MenuItem : RClass<MenuItem.Props> {

    interface Props : ListItem.Props

}
