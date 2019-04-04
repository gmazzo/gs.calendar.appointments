package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val dialogTitle = module.DialogTitle.unsafeCast<RClass<RProps>>()

fun RBuilder.dialogTitle(
    title: String,
    handler: (RHandler<RProps>) = {}
) = dialogTitle {
    +title

    handler(this)
}
