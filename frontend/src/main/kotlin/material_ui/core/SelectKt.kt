package material_ui.core

import react.RBuilder
import react.RHandler

fun RBuilder.uiSelect(handler: RHandler<Select.Props>) = select.invoke(handler)
