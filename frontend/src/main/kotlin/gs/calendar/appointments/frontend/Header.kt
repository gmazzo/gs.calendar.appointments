package gs.calendar.appointments.frontend

import css
import gs.calendar.appointments.model.Agenda
import kotlinx.css.Visibility
import kotlinx.css.flex
import kotlinx.css.margin
import kotlinx.css.px
import material_ui.core.AppBarPosition
import material_ui.core.ButtonColor
import material_ui.core.TypographyVariant
import material_ui.core.appBar
import material_ui.core.circularProgress
import material_ui.core.iconButton
import material_ui.core.position
import material_ui.core.toolBar
import material_ui.core.typography
import material_ui.icons.menuIcon
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

class Header : RComponent<Header.Props, Header.State>() {

    override fun RBuilder.render() {

    }

    interface Props : RProps

    data class State(
        var loading: Boolean,
        var currentAgenda: Agenda?
    ) : RState

}

fun RBuilder.header(currentAgenda: Agenda?, loading: Boolean) {
    appBar {
        attrs {
            position = AppBarPosition.STATIC
        }
        toolBar {
            iconButton(color = ButtonColor.INHERIT) {
                css {
                    margin(left = (-12).px, right = 20.px)
                }

                menuIcon()
            }
            typography(TypographyVariant.H6) {
                css {
                    flex(1.0)
                }
                +(currentAgenda?.description ?: BuildConfig.APP_NAME)
            }
            circularProgress {
                css {
                    margin(8.px)
                    if (!loading) {
                        visibility = Visibility.hidden
                    }
                }
            }
            agendasSelector(value = currentAgenda)
        }
    }
}
