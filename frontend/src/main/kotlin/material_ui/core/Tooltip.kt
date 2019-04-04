package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val tooltip = module.Tooltip.unsafeCast<Tooltip>()

fun RBuilder.tooltip(
    title: String,
    placement: TooltipPlacement = TooltipPlacement.BOTTOM,
    handler: (RHandler<Tooltip.Props>) = {}
) = tooltip {
    attrs.title = title
    attrs.placement = placement

    handler(this)
}

abstract external class Tooltip : RClass<Tooltip.Props> {

    interface Props : RProps {

        @JsName("placement")
        var placementValue: String

        var title: String

    }

}

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
    get() = placementValue.let { TooltipPlacement.values().find { v -> v.value == it }!! }
    set(value) {
        placementValue = value.value
    }
