package material_ui.core

import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val textField = module.TextField.unsafeCast<TextField>()

fun RBuilder.textField(
    autoFocus: Boolean? = null,
    disabled: Boolean? = null,
    error: Boolean? = null,
    fullWidth: Boolean? = null,
    label: String? = null,
    margin: TextFieldMargin? = null,
    multiline: Boolean? = null,
    onChange: ((MouseEvent) -> Unit)? = null,
    placeholder: String? = null,
    required: Boolean? = null,
    type: String? = null,
    value: String? = null,
    variant: TextFieldVariant? = null,
    handler: (RHandler<TextField.Props>) = {}
) = textField.invoke {
    autoFocus?.let { attrs.autoFocus = it }
    disabled?.let { attrs.disabled = it }
    error?.let { attrs.error = it }
    fullWidth?.let { attrs.fullWidth = it }
    label?.let { attrs.label = it }
    margin?.let { attrs.margin = it }
    multiline?.let { attrs.multiline = it }
    onChange?.let { attrs.onChange = it }
    placeholder?.let { attrs.placeholder = it }
    required?.let { attrs.required = it }
    type?.let { attrs.type = it }
    value?.let { attrs.value = it }
    variant?.let { attrs.variant = it }

    handler(this)
}

abstract external class TextField : RClass<TextField.Props> {

    interface Props : RProps {

        var autoFocus: Boolean

        var disabled: Boolean

        var error: Boolean

        var fullWidth: Boolean

        var label: String

        @JsName("margin")
        var marginValue: String

        var multiline: Boolean

        var onChange: (MouseEvent) -> Unit

        var placeholder: String

        var required: Boolean

        var type: String

        var value: String

        @JsName("variant")
        var variantValue: String

    }

}

enum class TextFieldMargin(val value: String) {
    NONE("none"),
    DENSE("dense"),
    NORMAL("normal")
}

enum class TextFieldVariant(val value: String) {
    STANDARD("standard"),
    OUTLINED("outlined"),
    FILLED("filled")
}

var TextField.Props.margin
    get() = marginValue.let { TextFieldMargin.values().find { v -> v.value == it }!! }
    set(value) {
        marginValue = value.value
    }

var TextField.Props.variant
    get() = variantValue.let { TextFieldVariant.values().find { v -> v.value == it }!! }
    set(value) {
        variantValue = value.value
    }
