package material_ui.core

import react.RBuilder
import react.RHandler

fun RBuilder.uiMenuItem(handler: (RHandler<MenuItem.Props>) = {}) =
    menuItem.invoke(handler)
