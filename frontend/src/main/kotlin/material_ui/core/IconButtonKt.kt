package material_ui.core

import react.RBuilder
import react.RHandler

fun RBuilder.uiIconButton(
    color: ButtonColor = ButtonColor.INHERIT,
    handler: (RHandler<Button.Props>) = {}
) = iconButton {
    attrs.color = color
    handler(this)
}
