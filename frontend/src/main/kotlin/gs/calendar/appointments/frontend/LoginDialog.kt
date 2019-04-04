package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.redux.ChangeUser
import gs.calendar.appointments.frontend.redux.store
import gs.calendar.appointments.model.User
import kotlinext.js.js
import material_ui.core.button
import material_ui.core.dialog
import material_ui.core.dialogActions
import material_ui.core.dialogContent
import material_ui.core.dialogContentText
import material_ui.core.dialogTitle
import notistack.WithSnackbar
import notistack.enqueueSnackbar
import onClick
import react.RBuilder

fun RBuilder.loginDialog(visible: Boolean, props: WithSnackbar) {
    dialog(open = visible) {
        dialogTitle(title = "Account required")
        dialogContent {
            dialogContentText(text = "Sign-in with your Google account to book appointments")
        }
        dialogActions {
            button(label = "LogIn") {
                onClick {
                    // TODO implement google login
                    val user = js {
                        name = "John Doe"
                        email = "john.doe@dummy.com"
                    }.unsafeCast<User>()

                    store.dispatch(ChangeUser(user))

                    props.enqueueSnackbar("Logged as ${user.name} <${user.email}>")
                }
            }
        }
    }
}
