package material_ui.core

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
