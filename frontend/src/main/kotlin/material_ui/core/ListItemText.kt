package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val listItemText = module.ListItemText.unsafeCast<ListItemText>()

fun RBuilder.listItemText(
    primary: String,
    secondary: String = "",
    handler: (RHandler<ListItemText.Props>) = {}
) = listItemText {
    attrs.primary = primary
    attrs.secondary = secondary

    handler(this)
}

abstract external class ListItemText : RClass<ListItemText.Props> {

    interface Props : RProps {
        var primary: String
        var secondary: String
    }

}
