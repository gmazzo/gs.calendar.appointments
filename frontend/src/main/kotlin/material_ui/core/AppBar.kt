package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val appBar = module.AppBar.unsafeCast<AppBar>()

fun RBuilder.appBar(
    position: AppBarPosition = AppBarPosition.FIXED,
    handler: (RHandler<AppBar.Props>) = {}
) = appBar.invoke {
    attrs.position = position

    handler(this)
}

@JsModule("@material-ui/core")
abstract external class AppBar : RClass<AppBar.Props> {

    interface Props : RProps {

        @JsName("position")
        var positionValue: String

    }

}

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
