package material_ui.core

import react.RBuilder
import react.RHandler

fun RBuilder.uiListItemText(
    primary: String,
    secondary: String = "",
    handler: (RHandler<ListItemText.Props>) = {}
) = listItemText {
    attrs.primary = primary
    attrs.secondary = secondary
    handler(this)
}
