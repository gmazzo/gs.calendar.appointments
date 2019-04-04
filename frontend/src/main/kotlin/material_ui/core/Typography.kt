package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val typography = module.Typography.unsafeCast<Typography>()

fun RBuilder.typography(
    variant: TypographyVariant,
    color: TypographyColor = TypographyColor.INHERIT,
    handler: (RHandler<Typography.Props>) = {}
) =
    typography {
        attrs.variant = variant
        attrs.color = color

        handler(this)
    }

abstract external class Typography : RClass<Typography.Props> {

    interface Props : RProps {

        @JsName("align")
        var alignValue: String

        @JsName("color")
        var colorValue: String

        var inline: Boolean

        var noWrap: Boolean

        var paragraph: Boolean

        @JsName("variant")
        var variantValue: String

    }

}

enum class TypographyAlign(val value: String) {
    INHERIT("inherit"),
    LEFT("left"),
    CENTER("center"),
    RIGHT("right"),
    JUSTIFY("justify")
}

enum class TypographyColor(val value: String) {
    DEFAULT("default"),
    ERROR("error"),
    INHERIT("inherit"),
    PRIMARY("primary"),
    SECONDARY("secondary"),
    TEXT_PRIMARY("textPrimary"),
    TEXT_SECONDARY("textSecondary")
}

enum class TypographyVariant(val value: String) {
    H1("h1"),
    H2("h2"),
    H3("h3"),
    H4("h4"),
    H5("h5"),
    H6("h6"),
    SUBTITLE1("subtitle1"),
    SUBTITLE2("subtitle2"),
    BODY1("body1"),
    BODY2("body2"),
    CAPTION("caption"),
    BUTTON("button"),
    OVERLINE("overline"),
    SRONLY("srOnly"),
    INHERIT("inherit"),
    DISPLAY1("display1"),
    DISPLAY2("display2"),
    DISPLAY3("display3"),
    DISPLAY4("display4"),
    HEADLINE("headline"),
    TITLE("title"),
    SUBHEADING("subheading")
}

var Typography.Props.align
    get() = alignValue.let { TypographyAlign.values().find { v -> v.value == it }!! }
    set(value) {
        alignValue = value.value
    }

var Typography.Props.color
    get() = colorValue.let { TypographyColor.values().find { v -> v.value == it }!! }
    set(value) {
        colorValue = value.value
    }

var Typography.Props.variant
    get() = variantValue.let { TypographyVariant.values().find { v -> v.value == it }!! }
    set(value) {
        variantValue = value.value
    }
