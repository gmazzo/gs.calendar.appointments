package material_ui.core

import react.RBuilder
import react.RHandler

fun RBuilder.uiListItem(
    button: Boolean = true,
    handler: (RHandler<ListItem.Props>) = {}
) =
    listItem {
        attrs.button = button
        handler(this)
    }
