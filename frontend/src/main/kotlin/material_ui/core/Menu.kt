package material_ui.core

import org.w3c.dom.Element
import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val menu = module.Menu.unsafeCast<Menu>()

fun RBuilder.menu(handler: (RHandler<Menu.Props>) = {}) =
    menu.invoke(handler)

abstract external class Menu : RClass<Menu.Props> {

    interface Props : RProps {

        var anchorEl: Element?

        var onClose: (MouseEvent) -> Unit

        var open: Boolean

    }

}
