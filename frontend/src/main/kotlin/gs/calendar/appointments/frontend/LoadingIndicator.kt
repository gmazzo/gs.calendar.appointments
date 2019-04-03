package gs.calendar.appointments.frontend

import css
import kotlinx.css.margin
import kotlinx.css.px
import material_ui.core.circularProgress
import material_ui.core.styles.WithTheme
import material_ui.core.styles.withTheme
import react.RBuilder
import react.RComponent
import react.RState

class LoadingIndicator : RComponent<LoadingIndicator.Props, RState>() {

    override fun RBuilder.render() {
        if (props.visible) {
            circularProgress {
                css { margin(props.theme.spacing.unit.px) }
            }
        }
    }

    interface Props : WithTheme {
        var visible: Boolean
    }

}

private val wrapped = withTheme(LoadingIndicator::class)

fun RBuilder.loadingIndicator(visible: Boolean) = child(wrapped) {
    attrs.visible = visible
}
