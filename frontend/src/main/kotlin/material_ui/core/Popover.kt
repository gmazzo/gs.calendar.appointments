package material_ui.core

import kotlinext.js.jsObject
import org.w3c.dom.Element
import org.w3c.dom.events.MouseEvent
import react.RProps

external interface PopoverProps : RProps {

    var anchorEl: Element?

    var anchorOrigin: AnchorOrigin

    var onClose: (MouseEvent) -> Unit

    var open: Boolean

    interface AnchorOrigin {

        @JsName("vertical")
        var verticalValue: String

        @JsName("horizontal")
        var horizontalValue: String

    }

}

enum class PopoverAnchorVerticalOrigin(val value: String) {
    TOP("top"),
    CENTER("center"),
    BOTTOM("bottom")
}

enum class PopoverAnchorHorizontalOrigin(val value: String) {
    LEFT("left"),
    CENTER("center"),
    RIGHT("right")
}

var PopoverProps.AnchorOrigin.vertical
    get() = verticalValue.let { PopoverAnchorVerticalOrigin.values().find { v -> v.value == it }!! }
    set(value) {
        verticalValue = value.value
    }

var PopoverProps.AnchorOrigin.horizontal
    get() = horizontalValue.let { PopoverAnchorHorizontalOrigin.values().find { v -> v.value == it }!! }
    set(value) {
        horizontalValue = value.value
    }

fun anchorOrigin(
    vertical: PopoverAnchorVerticalOrigin,
    horizontal: PopoverAnchorHorizontalOrigin
): PopoverProps.AnchorOrigin = jsObject {
    this.vertical = vertical
    this.horizontal = horizontal
}
