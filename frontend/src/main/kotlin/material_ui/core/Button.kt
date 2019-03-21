@file:JsModule("@material-ui/core")

package material_ui.core

import org.w3c.dom.events.Event
import react.RClass
import react.RProps

abstract external class Button : RClass<Button.Props> {

    interface Props : RProps {
        var onClick: (Event?) -> Unit
        var href: String
    }

}

@JsName("Button")
external val uiButton: Button
