package material_ui.core

import react.RBuilder
import react.RHandler

fun RBuilder.uiList(handler: (RHandler<List.Props>) = {}) =
    list.invoke(handler)
