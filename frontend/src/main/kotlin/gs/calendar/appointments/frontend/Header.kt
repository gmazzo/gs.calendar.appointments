package gs.calendar.appointments.frontend

import jsStyle
import material_ui.core.AppBarPosition
import material_ui.core.ButtonColor
import material_ui.core.TypographyVariant
import material_ui.core.position
import material_ui.core.uiAppBar
import material_ui.core.uiDrawer
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

class Header : RComponent<Header.Props, Header.State>() {

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
                    attrs {
                        onClick = { setState { drawerOpen = !drawerOpen } }
                    }
                    uiMenuIcon {}
                }
                uiTypography(TypographyVariant.H6) {
                    jsStyle {
                        flex = 1
                    }
                    +BuildConfig.APP_NAME
                }
            }
        }
        uiDrawer {
            attrs {
                open = state.drawerOpen
                onClose = { setState { drawerOpen = false } }
            }
            agendasSelector { }
        }
    }

    interface Props : RProps

    data class State(
        var drawerOpen: Boolean = false
    ) : RState

}

fun RBuilder.header(handler: RHandler<Header.Props>) =
    child(Header::class, handler)