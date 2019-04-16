package material_ui.core

import css
import kotlinx.css.Position
import kotlinx.css.px
import material_ui.icons.Icons
import material_ui.icons.icon
import onClick
import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val dialogTitle = module.DialogTitle.unsafeCast<RClass<RProps>>()

fun RBuilder.dialogTitle(
    title: String,
    onClose: ((MouseEvent) -> Unit)? = null,
    handler: (RHandler<RProps>) = {}
) = dialogTitle {
    +title

    onClose?.let {
        iconButton {
            css {
                position = Position.absolute
                top = 6.px
                right = 6.px
            }
            icon(Icons.CLOSE)
            onClick(it)
        }
    }

    handler(this)
}
