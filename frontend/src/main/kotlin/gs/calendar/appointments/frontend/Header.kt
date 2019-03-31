package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.redux.appStore
import jsStyle
import material_ui.core.AppBarPosition
import material_ui.core.ButtonColor
import material_ui.core.TypographyVariant
import material_ui.core.position
import material_ui.core.uiAppBar
import material_ui.core.uiCircularProgress
import material_ui.core.uiIconButton
import material_ui.core.uiToolBar
import material_ui.core.uiTypography
import material_ui.icons.uiMenuIcon
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RProps
import react.RState
import react.setState
import redux.state

class Header : RComponent<Header.Props, Header.State>() {

    private lateinit var unsubscribe: () -> Unit

    override fun componentWillMount() {
        unsubscribe = appStore.subscribe { setState { loading = appStore.state.loading } }
    }

    override fun componentWillUnmount() {
        unsubscribe()
    }

    override fun RBuilder.render() {
        uiAppBar {
            attrs {
                position = AppBarPosition.STATIC
            }
            uiToolBar {
                uiIconButton(color = ButtonColor.INHERIT) {
                    jsStyle {
                        marginLeft = -12
                        marginRight = 20
                    }

                    uiMenuIcon {}
                }
                uiTypography(TypographyVariant.H6) {
                    jsStyle {
                        flex = 1
                    }
                    +BuildConfig.APP_NAME
                }
                agendasSelector { }

                if (state.loading) {
                    uiCircularProgress()
                }
            }
        }
    }

    interface Props : RProps

    data class State(
        var loading: Boolean
    ) : RState

}

fun RBuilder.header(handler: (RHandler<Header.Props>) = {}) =
    child(Header::class, handler)