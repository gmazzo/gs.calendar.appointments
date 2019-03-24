@file:JsModule("@material-ui/core")

package material_ui.core

import react.RClass
import react.RProps

abstract external class Typography : RClass<Typography.Props> {

    interface Props : RProps {
        @JsName("align")
        var alignValue: String
        @JsName("color")
        var colorValue: String
        var inline: Boolean
        var noWrap: Boolean
        var paragraph: Boolean
        @JsName("variant")
        var variantValue: String
    }

}

@JsName("Typography")
external val typography: Typography
