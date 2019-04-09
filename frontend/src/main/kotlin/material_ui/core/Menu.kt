package material_ui.core

import org.w3c.dom.Element
import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val menu = module.Menu.unsafeCast<Menu>()

fun RBuilder.menu(
    anchorEl: Element?,
    onClose: (MouseEvent) -> Unit,
    `open`: Boolean,
    anchorOrigin: PopoverProps.AnchorOrigin? = null,
    handler: (RHandler<Menu.Props>) = {}
) = menu {
    attrs.anchorEl = anchorEl
    attrs.onClose = onClose
    attrs.open = open
    anchorOrigin?.let { attrs.anchorOrigin = it }

    handler(this)
}

abstract external class Menu : RClass<Menu.Props> {

    interface Props : PopoverProps {

        @JsName("MenuListProps")
        var menuListProps: RProps

    }

}
