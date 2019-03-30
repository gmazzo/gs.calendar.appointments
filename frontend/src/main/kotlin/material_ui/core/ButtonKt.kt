package material_ui.core

import react.RBuilder
import react.RHandler

enum class ButtonColor(val value: String) {
    DEFAULT("default"),
    INHERIT("inherit"),
    PRIMARY("primary"),
    SECONDARY("secondary")
}

enum class ButtonSize(val value: String) {
    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large")
}

enum class ButtonVariant(val value: String) {
    TEXT("text"),
    OUTLINED("outlined"),
    CONTAINED("contained"),
    FAB("fab"),
    EXTENDED_FAB("extendedFab"),
    FLAT("flat"),
    RAISED("raised")
}

var Button.Props.color
    get() = ButtonColor.valueOf(colorValue.toUpperCase())
    set(value) {
        colorValue = value.name.toLowerCase()
    }

var Button.Props.size
    get() = ButtonSize.valueOf(sizeValue.toUpperCase())
    set(value) {
        sizeValue = value.name.toLowerCase()
    }

var Button.Props.variant
    get() = ButtonVariant.valueOf(variantValue.toUpperCase())
    set(value) {
        variantValue = value.name.toLowerCase()
    }

fun RBuilder.uiButton(
    label: String,
    variant: ButtonVariant = ButtonVariant.CONTAINED,
    color: ButtonColor = ButtonColor.INHERIT,
    handler: (RHandler<Button.Props>) = {}
) =
    button {
        attrs.variant = variant
        attrs.color = color

        +label
        handler(this)
    }
