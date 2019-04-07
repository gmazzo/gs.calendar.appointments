package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.redux.ChangeUser
import gs.calendar.appointments.frontend.redux.StartLoading
import gs.calendar.appointments.frontend.redux.StopLoading
import gs.calendar.appointments.frontend.redux.dispatch
import gs.calendar.appointments.model.User
import material_ui.core.dialog
import material_ui.core.dialogActions
import material_ui.core.dialogContent
import material_ui.core.dialogContentText
import material_ui.core.dialogTitle
import notistack.SnackbarVariant
import notistack.WithSnackbar
import notistack.enqueueSnackbar
import react.RBuilder
import react_google_login.googleLogin

fun RBuilder.loginDialog(currentUser: User?, withSnackbar: WithSnackbar) {
    dialog(open = currentUser == null) {
        dialogTitle("Account required")
        dialogContent {
            dialogContentText("Sign-in with your Google account to book appointments")
        }
        dialogActions {
            googleLogin(
                // TODO move this to a build property
                clientId = "752118259594-201e8779d52re6d2lr2pkrca4fjt2tbj.apps.googleusercontent.com",
                buttonText = "Google Login",
                isSignedIn = true,
                onRequest = { StartLoading.dispatch() },
                onSuccess = {
                    val user = with(it.profileObj) { User(googleId, name, email, imageUrl) }

                    ChangeUser(user).dispatch()
                    withSnackbar.enqueueSnackbar("Logged as ${user.name} <${user.email}>")
                    StopLoading.dispatch()
                },
                onFailure = {
                    console.error(it)

                    withSnackbar.enqueueSnackbar(it.details ?: it.errorValue, variant = SnackbarVariant.ERROR)
                    StopLoading.dispatch()
                }
            )
        }
    }
}
