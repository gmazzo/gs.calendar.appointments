package material_ui.core

import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val select = module.Select.unsafeCast<Select>()

fun RBuilder.select(
    value: String? = null,
    handler: (RHandler<Select.Props>) = {}
) =
    select.invoke {
        value?.let { attrs.value = it }

        handler(this)
    }

abstract external class Select : RClass<Select.Props> {

    interface Props : RProps {

        var autoWidth: Boolean

        var value: String

        var onChange: (MouseEvent) -> Unit

    }

}
