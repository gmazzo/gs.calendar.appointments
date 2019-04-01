package material_ui.core

import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val listItem = module.ListItem.unsafeCast<ListItem>()

fun RBuilder.listItem(
    button: Boolean = true,
    handler: (RHandler<ListItem.Props>) = {}
) = listItem.invoke {
    attrs.button = button

    handler(this)
}

abstract external class ListItem : RClass<ListItem.Props> {

    interface Props : RProps {
        var button: Boolean
        var disabled: Boolean
        var divider: Boolean
        var selected: Boolean
        var onClick: (MouseEvent) -> Unit
    }

}
