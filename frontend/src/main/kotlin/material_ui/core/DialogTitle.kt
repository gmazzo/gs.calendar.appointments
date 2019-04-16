package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val dialogTitle = module.DialogTitle.unsafeCast<DialogTitle>()

fun RBuilder.dialogTitle(
    title: String? = null,
    disableTypography: Boolean? = null,
    handler: (RHandler<DialogTitle.Props>) = {}
) = dialogTitle.invoke {
    disableTypography?.let { attrs.disableTypography = disableTypography }
    title?.let { +it }

    handler(this)
}

external interface DialogTitle : RClass<DialogTitle.Props> {

    interface Props : RProps {

        var disableTypography: Boolean

    }

}
