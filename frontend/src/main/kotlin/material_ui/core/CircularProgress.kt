@file:JsModule("@material-ui/core")

package material_ui.core

import react.RClass
import react.RProps

abstract external class CircularProgress : RClass<CircularProgress.Props> {

    interface Props : RProps {

        @JsName("color")
        var colorValue: String

        @JsName("variant")
        var variantValue: String

    }

}

@JsName("CircularProgress")
external val circularProgress: CircularProgress
