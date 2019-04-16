import kotlinx.css.Display
import kotlinx.css.TextAlign
import kotlinx.css.px
import material_ui.core.TypographyVariant
import material_ui.core.dialogTitle
import material_ui.core.typography
import react.RBuilder
import react.dom.div

fun RBuilder.dialogTitleWithActions(
    title: String? = null,
    handler: RBuilder.() -> Unit
) = dialogTitleWithActions(
    title = { title?.let { typography(variant = TypographyVariant.TITLE) { +it } } },
    handler = handler
)

fun RBuilder.dialogTitleWithActions(
    title: RBuilder.() -> Unit,
    handler: RBuilder.() -> Unit
) {
    dialogTitle(disableTypography = true) {
        css { display = Display.flex }

        title()

        div {
            css {
                marginTop = (-20).px
                marginRight = (-20).px
                flexGrow = 1.0
                textAlign = TextAlign.end
            }

            handler()
        }
    }
}
