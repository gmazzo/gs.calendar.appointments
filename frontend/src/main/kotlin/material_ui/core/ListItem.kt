package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val listItem = module.ListItem.unsafeCast<ListItem>()

fun RBuilder.listItem(
    button: Boolean? = null,
    disabled: Boolean? = null,
    divider: Boolean? = null,
    selected: Boolean? = null,
    handler: (RHandler<ListItem.Props>) = {}
) = listItem.invoke {
    button?.let { attrs.button = it }
    disabled?.let { attrs.disabled = it }
    divider?.let { attrs.divider = it }
    selected?.let { attrs.selected = it }

    handler(this)
}

abstract external class ListItem : RClass<ListItem.Props> {

    interface Props : RProps {
        var button: Boolean
        var disabled: Boolean
        var divider: Boolean
        var selected: Boolean
    }

}
