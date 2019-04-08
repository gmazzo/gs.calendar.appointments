package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler

private val menuItem = module.MenuItem.unsafeCast<MenuItem>()

fun RBuilder.menuItem(
    button: Boolean? = null,
    disabled: Boolean? = null,
    divider: Boolean? = null,
    selected: Boolean? = null,
    handler: (RHandler<ListItem.Props>) = {}
) = menuItem.invoke {
    button?.let { attrs.button = it }
    disabled?.let { attrs.disabled = it }
    divider?.let { attrs.divider = it }
    selected?.let { attrs.selected = it }

    handler(this)
}

abstract external class MenuItem : RClass<MenuItem.Props> {

    interface Props : ListItem.Props

}
