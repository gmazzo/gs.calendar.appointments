package material_ui.core

import react.RBuilder
import react.RHandler

fun RBuilder.uiSelect(
    value: String? = null,
    handler: (RHandler<Select.Props>) = {}
) =
    select {
        attrs.value = value
        handler(this)
    }
