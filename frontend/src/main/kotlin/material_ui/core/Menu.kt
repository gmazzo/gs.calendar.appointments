@file:JsModule("@material-ui/core")

package material_ui.core

import org.w3c.dom.Element
import org.w3c.dom.events.MouseEvent
import react.RClass
import react.RProps

abstract external class Menu : RClass<Menu.Props> {

    interface Props : RProps {

        var anchorEl: Element?

        var onClose: (MouseEvent) -> Unit

        var open: Boolean

    }

}

@JsName("Menu")
external val menu: Menu
