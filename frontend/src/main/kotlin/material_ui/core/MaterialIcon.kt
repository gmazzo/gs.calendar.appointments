@file:JsModule("@material-ui/core")

package material_ui.core

import org.w3c.dom.events.Event
import react.RClass
import react.RProps

external interface MaterialIconProps : RProps {
    var icon: String
    var onClick: ((Event?) -> Unit)?
}

@JsName("MaterialIcon")
external val MaterialIcon: RClass<MaterialIconProps>
