@file:JsModule("@material-ui/core")

package material_ui.core

import org.w3c.dom.events.Event
import react.RClass
import react.RProps

external interface ButtonProps : RProps {
    var className: String
    var onClick: (Event?) -> Unit
    var color: String
    var href: String
}

@JsName("Button")
external val Button: RClass<ButtonProps>
