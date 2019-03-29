@file:JsModule("@material-ui/core")

package material_ui.core

import org.w3c.dom.events.MouseEvent
import react.RClass
import react.RProps

abstract external class Button : RClass<Button.Props> {

    interface Props : RProps {

        @JsName("color")
        var colorValue: String

        var disabled: Boolean

        var fullWidth: Boolean

        var href: String

        @JsName("size")
        var sizeValue: String

        @JsName("variant")
        var variantValue: String

        var onClick: (MouseEvent) -> Unit

    }

}

@JsName("Button")
external val button: Button
