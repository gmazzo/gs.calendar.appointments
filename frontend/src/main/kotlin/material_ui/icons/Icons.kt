package material_ui.icons

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

typealias Icon = RClass<RProps>

@JsModule("@material-ui/icons")
external object Icons {

    @JsName("AccountCircle")
    val ACCOUNT_CIRCLE: Icon

    @JsName("Close")
    val CLOSE: Icon

    @JsName("Menu")
    val MENU: Icon

}

fun RBuilder.icon(
    icon: Icon,
    handler: (RHandler<RProps>) = {}
) = icon(handler)
