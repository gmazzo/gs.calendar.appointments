package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps
import react.ReactElement
import react.buildElement

private val chip = module.Chip.unsafeCast<Chip>()

fun RBuilder.chip(
    color: ChipColor? = null,
    avatar: (RBuilder.() -> Unit)? = null,
    label: String? = null,
    onDelete: (() -> Unit)? = null,
    handler: (RHandler<Chip.Props>) = {}
) = chip.invoke {
    color?.let { attrs.color = it }
    avatar?.let { attrs.avatar = buildElement(avatar) }
    label?.let { attrs.label = it }
    onDelete?.let { attrs.onDelete = it }

    handler(this)
}

external interface Chip : RClass<Chip.Props> {

    interface Props : RProps {

        @JsName("color")
        var colorValue: String

        var avatar: ReactElement?

        var label: String

        var onDelete: () -> Unit

    }

}

enum class ChipColor(val value: String) {
    DEFAULT("default"),
    PRIMARY("primary"),
    SECONDARY("secondary")
}

var Chip.Props.color
    get() = colorValue.let { ChipColor.values().find { v -> v.value == it }!! }
    set(value) {
        colorValue = value.value
    }
