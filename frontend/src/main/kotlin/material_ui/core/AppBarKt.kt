package material_ui.core

import react.RBuilder
import react.RHandler

enum class AppBarPosition(val value: String) {
    FIXED("fixed"),
    ABSOLUTE("absolute"),
    STICKY("sticky"),
    STATIC("static"),
    RELATIVE("relative")
}

var AppBar.Props.position
    get() = AppBarPosition.valueOf(positionValue.toUpperCase())
    set(value) {
        positionValue = value.name.toLowerCase()
    }

fun RBuilder.uiAppBar(
    position: AppBarPosition = AppBarPosition.FIXED,
    handler: (RHandler<AppBar.Props>) = {}
) = appBar {
    attrs.position = position
    handler(this)
}
