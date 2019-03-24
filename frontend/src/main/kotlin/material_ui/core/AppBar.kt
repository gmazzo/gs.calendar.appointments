@file:JsModule("@material-ui/core")

package material_ui.core

import react.RClass
import react.RProps

abstract external class AppBar : RClass<AppBar.Props> {

    interface Props : RProps {

        var className: String

        @JsName("position")
        var positionValue: String

    }

}

@JsName("AppBar")
external val appBar: AppBar
