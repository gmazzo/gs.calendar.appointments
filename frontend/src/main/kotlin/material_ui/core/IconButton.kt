package material_ui.core

import react.RBuilder
import react.RHandler

abstract external class IconButton : Button

private val iconButton = module.IconButton.unsafeCast<IconButton>()

fun RBuilder.iconButton(
    color: ButtonColor? = null,
    handler: (RHandler<Button.Props>) = {}
) = iconButton.invoke {
    color?.let { attrs.color = it }

    handler(this)
}
