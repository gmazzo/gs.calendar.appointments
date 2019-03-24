package material_ui.core

import react.RBuilder
import react.RElementBuilder

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
    get() = TypographyAlign.valueOf(alignValue.toUpperCase())
    set(value) {
        alignValue = value.name.toLowerCase()
    }

var Typography.Props.color
    get() = TypographyColor.valueOf(colorValue.toUpperCase())
    set(value) {
        colorValue = value.name.toLowerCase()
    }

var Typography.Props.variant
    get() = TypographyVariant.valueOf(variantValue.toUpperCase())
    set(value) {
        variantValue = value.name.toLowerCase()
    }

fun RBuilder.uiTypography(
    variant: TypographyVariant,
    color: TypographyColor = TypographyColor.INHERIT,
    handler: RElementBuilder<Typography.Props>.() -> Unit
) =
    uiTypography {
        attrs {
            this.variant = variant
            this.color = color
        }
        handler(this)
    }
