package material_ui.core

import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val list = module.List.unsafeCast<List>()

fun RBuilder.list(handler: (RHandler<List.Props>) = {}) =
    list.invoke(handler)

abstract external class List : RClass<List.Props> {

    interface Props : RProps {

        var onClick: (MouseEvent) -> Unit

    }

}
