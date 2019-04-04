package material_ui.core

import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val drawer = module.Drawer.unsafeCast<Drawer>()

fun RBuilder.drawer(
    variant: DrawerVariant = DrawerVariant.TEMPORARY,
    anchor: DrawerAnchor = DrawerAnchor.LEFT,
    handler: (RHandler<Drawer.Props>) = {}
) = drawer.invoke {
    attrs.variant = variant
    attrs.anchor = anchor
    handler(this)
}

abstract external class Drawer : RClass<Drawer.Props> {

    interface Props : RProps {

        @JsName("anchor")
        var anchorValue: String

        var onClose: (MouseEvent) -> Unit

        var open: Boolean

        @JsName("variant")
        var variantValue: String

    }

}

enum class DrawerAnchor(val value: String) {
    LEFT("left"),
    TOP("top"),
    RIGHT("right"),
    BOTTOM("bottom")
}

enum class DrawerVariant(val value: String) {
    PERMANENT("permanent"),
    PERSISTENT("persistent"),
    TEMPORARY("temporary")
}

var Drawer.Props.anchor
    get() = anchorValue.let { DrawerAnchor.values().find { v -> v.value == it }!! }
    set(value) {
        anchorValue = value.value
    }

var Drawer.Props.variant
    get() = variantValue.let { DrawerVariant.values().find { v -> v.value == it }!! }
    set(value) {
        variantValue = value.value
    }
