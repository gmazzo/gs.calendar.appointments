@file:JsModule("@material-ui/core")

package material_ui.core

import react.RClass
import react.RProps

abstract external class List : RClass<List.Props> {

    interface Props : RProps

}

@JsName("List")
external val list: List
