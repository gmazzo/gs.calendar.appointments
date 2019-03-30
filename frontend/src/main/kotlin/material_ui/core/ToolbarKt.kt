package material_ui.core

import react.RBuilder
import react.RHandler

fun RBuilder.uiToolBar(handler: (RHandler<Toolbar.Props>) = {}) =
    toolBar.invoke(handler)
