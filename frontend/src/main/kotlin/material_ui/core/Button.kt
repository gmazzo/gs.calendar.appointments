package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val button = module.Button.unsafeCast<Button>()

fun RBuilder.button(
    label: String,
    variant: ButtonVariant = ButtonVariant.CONTAINED,
    color: ButtonColor = ButtonColor.INHERIT,
    handler: (RHandler<Button.Props>) = {}
) = button {
    attrs.variant = variant
    attrs.color = color

    +label
    handler(this)
}

abstract external class Button : RClass<Button.Props> {

    interface Props : RProps {

        @JsName("color")
        var colorValue: String

        var disabled: Boolean

        var fullWidth: Boolean

        var href: String

        @JsName("size")
        var sizeValue: String

        @JsName("variant")
        var variantValue: String

    }

}

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
    get() = colorValue.let { ButtonColor.values().find { v -> v.value == it }!! }
    set(value) {
        colorValue = value.value
    }

var Button.Props.size
    get() = sizeValue.let { ButtonSize.values().find { v -> v.value == it }!! }
    set(value) {
        sizeValue = value.value
    }

var Button.Props.variant
    get() = variantValue.let { ButtonVariant.values().find { v -> v.value == it }!! }
    set(value) {
        variantValue = value.value
    }
