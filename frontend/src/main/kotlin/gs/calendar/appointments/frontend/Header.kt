package gs.calendar.appointments.frontend

import css
import gs.calendar.appointments.model.Agenda
import kotlinx.css.Visibility
import kotlinx.css.flex
import kotlinx.css.margin
import kotlinx.css.px
import material_ui.core.AppBarPosition
import material_ui.core.TypographyVariant
import material_ui.core.appBar
import material_ui.core.circularProgress
import material_ui.core.position
import material_ui.core.toolBar
import material_ui.core.typography
import react.RBuilder

fun RBuilder.header(agendas: List<Agenda>?, currentAgenda: Agenda?, loading: Boolean) {
    appBar {
        attrs {
            position = AppBarPosition.STATIC
        }
        toolBar {
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
            agendasSelector(options = agendas, value = currentAgenda)
        }
    }
}
