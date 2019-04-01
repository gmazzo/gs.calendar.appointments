package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val circularProgress = module.CircularProgress.unsafeCast<CircularProgress>()

fun RBuilder.circularProgress(
    color: CircularProgressColor = CircularProgressColor.INHERIT,
    variant: CircularProgressVariant = CircularProgressVariant.INDETERMINATE,
    handler: (RHandler<CircularProgress.Props>) = {}
) = circularProgress.invoke {
    attrs.color = color
    attrs.variant = variant

    handler(this)

}

abstract external class CircularProgress : RClass<CircularProgress.Props> {

    interface Props : RProps {

        @JsName("color")
        var colorValue: String

        @JsName("variant")
        var variantValue: String

    }

}

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
