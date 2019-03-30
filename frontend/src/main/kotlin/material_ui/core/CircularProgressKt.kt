package material_ui.core

import react.RBuilder
import react.RHandler

enum class CircularProgressColor(val value: String) {
    INHERIT("inherit"),
    PRIMARY("primary"),
    SECONDARY("secondary")
}

enum class CircularProgressVariant(val value: String) {
    DETERMINATE("determinate"),
    INDETERMINATE("indeterminate"),
    STATIC("static")
}

var CircularProgress.Props.color
    get() = CircularProgressColor.valueOf(colorValue.toUpperCase())
    set(value) {
        colorValue = value.name.toLowerCase()
    }

var CircularProgress.Props.variant
    get() = CircularProgressVariant.valueOf(variantValue.toUpperCase())
    set(value) {
        variantValue = value.name.toLowerCase()
    }

fun RBuilder.uiCircularProgress(handler: RHandler<CircularProgress.Props>) =
    circularProgress.invoke(handler)
