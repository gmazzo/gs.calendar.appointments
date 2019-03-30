package material_ui.core

import react.RBuilder
import react.RHandler

fun RBuilder.uiMenu(handler: (RHandler<Menu.Props>) = {}) =
    menu.invoke(handler)
