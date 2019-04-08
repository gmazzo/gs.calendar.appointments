package gs.calendar.appointments.frontend.header

import css
import gs.calendar.appointments.frontend.BuildConfig
import gs.calendar.appointments.model.Agenda
import gs.calendar.appointments.model.User
import kotlinx.css.flex
import material_ui.core.AppBarPosition
import material_ui.core.TypographyVariant
import material_ui.core.appBar
import material_ui.core.position
import material_ui.core.toolBar
import material_ui.core.typography
import react.RBuilder

fun RBuilder.header(
    agendas: List<Agenda>?,
    currentAgenda: Agenda?,
    currentUser: User?,
    loading: Boolean
) {
    appBar {
        attrs {
            position = AppBarPosition.STATIC
        }
        toolBar {
            typography(TypographyVariant.H6) {
                css {
                    flex(1.0)
                }
                +(currentAgenda?.description ?: BuildConfig.APP_NAME.takeIf { !loading } ?: "")
            }
            loadingIndicator(visible = loading)
            agendasSelector(options = agendas, value = currentAgenda)
            if (currentUser != null) {
                accountMenu(user = currentUser)
            }
        }
    }
}
