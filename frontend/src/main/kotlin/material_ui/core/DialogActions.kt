package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val dialogActions = module.DialogActions.unsafeCast<RClass<RProps>>()

fun RBuilder.dialogActions(handler: (RHandler<RProps>) = {}) =
    dialogActions.invoke(handler)
