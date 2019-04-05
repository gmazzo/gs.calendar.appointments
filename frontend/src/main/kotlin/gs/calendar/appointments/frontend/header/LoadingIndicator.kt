package gs.calendar.appointments.frontend.header

import css
import kotlinx.css.margin
import kotlinx.css.px
import material_ui.core.circularProgress
import material_ui.core.styles.WithTheme
import material_ui.core.styles.withTheme
import react.RBuilder
import react.invoke

private interface Props : WithTheme {
    var visible: Boolean
}

private val wrapped = withTheme<Props>()() { props ->
    if (props.visible) {
        circularProgress {
            css { margin(props.theme.spacing.unit.px) }
        }
    }
}

fun RBuilder.loadingIndicator(visible: Boolean) = wrapped {
    attrs.visible = visible
}
