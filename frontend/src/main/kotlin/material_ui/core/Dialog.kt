package material_ui.core

import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val dialog = module.Dialog.unsafeCast<Dialog>()

fun RBuilder.dialog(
    `open`: Boolean = true,
    onClose: ((MouseEvent) -> Unit)? = null,
    handler: (RHandler<Dialog.Props>) = {}
) = dialog.invoke {
    attrs.open = open
    onClose?.let { attrs.onClose = it }

    handler(this)
}

external interface Dialog : RClass<Dialog.Props> {

    interface Props : RProps {

        var open: Boolean

        var onClose: (MouseEvent) -> Unit

    }

}
