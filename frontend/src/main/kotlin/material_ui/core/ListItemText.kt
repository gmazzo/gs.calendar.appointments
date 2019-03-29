@file:JsModule("@material-ui/core")

package material_ui.core

import react.RClass
import react.RProps

abstract external class ListItemText : RClass<ListItemText.Props> {

    interface Props : RProps {
        var primary: String
    }

}

@JsName("ListItemText")
external val listItemText: ListItemText
