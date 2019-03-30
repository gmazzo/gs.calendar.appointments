@file:JsModule("@material-ui/core")

package material_ui.core

import react.RClass

abstract external class MenuItem : RClass<MenuItem.Props> {

    interface Props : ListItem.Props

}

@JsName("MenuItem")
external val menuItem: MenuItem
