@file:JsModule("@material-ui/core")

package material_ui.core

import org.w3c.dom.events.MouseEvent
import react.RClass
import react.RProps

abstract external class Select : RClass<Select.Props> {

    interface Props : RProps {

        var autoWidth: Boolean

        var value: String?

        var onChange: (MouseEvent) -> Unit

    }

}

@JsName("Select")
external val select: Select
