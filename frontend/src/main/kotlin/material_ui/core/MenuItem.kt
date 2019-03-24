@file:JsModule("@material-ui/core")

package material_ui.core

import react.RClass
import react.RProps

abstract external class MenuItem : RClass<MenuItem.Props> {

    interface Props : RProps {

        var value: String

    }

}

@JsName("MenuItem")
external val uiMenuItem: MenuItem
