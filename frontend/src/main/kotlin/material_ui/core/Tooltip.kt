@file:JsModule("@material-ui/core")

package material_ui.core

import react.RClass
import react.RProps

abstract external class Tooltip : RClass<Tooltip.Props> {

    interface Props : RProps {

        @JsName("placement")
        var placementValue: String

        var title: String

    }

}

@JsName("Tooltip")
external val uiTooltip: Tooltip
