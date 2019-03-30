@file:JsModule("@material-ui/core")

package material_ui.core

import org.w3c.dom.events.MouseEvent
import react.RClass
import react.RProps

abstract external class ListItem : RClass<ListItem.Props> {

    interface Props : RProps {
        var button: Boolean
        var disabled: Boolean
        var divider: Boolean
        var selected: Boolean
        var onClick: (MouseEvent) -> Unit
    }

}

@JsName("ListItem")
external val listItem: ListItem
