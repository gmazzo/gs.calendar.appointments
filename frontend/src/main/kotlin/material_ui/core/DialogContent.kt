package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val dialogContent = module.DialogContent.unsafeCast<RClass<RProps>>()

fun RBuilder.dialogContent(handler: (RHandler<RProps>) = {}) =
    dialogContent.invoke(handler)
