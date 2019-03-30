@file:JsModule("@material-ui/core")

package material_ui.core

import org.w3c.dom.events.MouseEvent
import react.RClass
import react.RProps

abstract external class List : RClass<List.Props> {

    interface Props : RProps {

        var onClick: (MouseEvent) -> Unit

    }

}

@JsName("List")
external val list: List
