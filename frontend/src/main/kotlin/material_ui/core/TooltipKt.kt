package material_ui.core

import react.RBuilder
import react.RHandler

enum class TooltipPlacement(val value: String) {
    BOTTOM_END("bottom-end"),
    BOTTOM_START("bottom-start"),
    BOTTOM("bottom"),
    LEFT_END("left-end"),
    LEFT_START("left-start"),
    LEFT("left"),
    RIGHT_END("right-end"),
    RIGHT_START("right-start"),
    RIGHT("right"),
    TOP_END("top-end"),
    TOP_START("top-start"),
    TOP("top")
}

var Tooltip.Props.placement
    get() = TooltipPlacement.valueOf(placementValue.toUpperCase())
    set(value) {
        placementValue = value.name.toLowerCase()
    }

fun RBuilder.uiTooltip(
    title: String,
    placement: TooltipPlacement = TooltipPlacement.BOTTOM,
    handler: (RHandler<Tooltip.Props>) = {}
) = tooltip {
    attrs.title = title
    attrs.placement = placement
    handler(this)
}
