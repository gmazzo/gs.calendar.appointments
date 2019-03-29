@file:JsModule("@material-ui/core")

package material_ui.core

import react.RClass
import react.RProps

abstract external class ListItem : RClass<ListItem.Props> {

    interface Props : RProps {
        var button: Boolean
        var key: String
    }

}

@JsName("ListItem")
external val listItem: ListItem
