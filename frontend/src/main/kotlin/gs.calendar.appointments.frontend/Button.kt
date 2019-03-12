@file:JsModule("@material-ui/core/Button")

package gs.calendar.appointments.frontend

import org.w3c.dom.events.Event
import react.RClass
import react.RProps

external interface ButtonProps : RProps {
    var className: String
    var onClick: (Event?) -> Unit
    var color: String
    var href: String
}

@JsName("default")
external val Button: RClass<ButtonProps>
