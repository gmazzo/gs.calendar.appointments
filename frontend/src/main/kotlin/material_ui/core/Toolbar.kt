@file:JsModule("@material-ui/core")

package material_ui.core

import react.RClass
import react.RProps

abstract external class Toolbar : RClass<Toolbar.Props> {

    interface Props : RProps

}

@JsName("Toolbar")
external val toolBar: Toolbar
