package material_ui.core

import react.RBuilder
import react.RHandler

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
    get() = DrawerAnchor.valueOf(anchorValue.toUpperCase())
    set(value) {
        anchorValue = value.name.toLowerCase()
    }

var Drawer.Props.variant
    get() = DrawerVariant.valueOf(variantValue.toUpperCase())
    set(value) {
        variantValue = value.name.toLowerCase()
    }

fun RBuilder.uiDrawer(
    variant: DrawerVariant = DrawerVariant.TEMPORARY,
    anchor: DrawerAnchor = DrawerAnchor.LEFT,
    handler: (RHandler<Drawer.Props>) = {}
) = drawer {
    attrs.variant = variant
    attrs.anchor = anchor
    handler(this)
}
