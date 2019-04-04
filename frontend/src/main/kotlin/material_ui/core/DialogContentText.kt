package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val dialogContentText = module.DialogContentText.unsafeCast<RClass<RProps>>()

fun RBuilder.dialogContentText(
    text: String,
    handler: (RHandler<RProps>) = {}
) = dialogContentText {
    +text

    handler(this)
}
