package gs.calendar.appointments.frontend

import gs.calendar.appointments.frontend.redux.ChangeUser
import gs.calendar.appointments.frontend.redux.store
import gs.calendar.appointments.model.User
import material_ui.core.dialog
import material_ui.core.dialogActions
import material_ui.core.dialogContent
import material_ui.core.dialogContentText
import material_ui.core.dialogTitle
import notistack.SnackbarVariant
import notistack.WithSnackbar
import notistack.enqueueSnackbar
import notistack.withSnackbar
import react.RBuilder
import react.invoke
import react_google_login.GoogleLogin
import react_google_login.googleLogin

private interface Props : WithSnackbar {
    var currentUser: User?
}

private val wrapped = withSnackbar<Props>()() { props ->

    fun onLoggedIn(response: GoogleLogin.SuccessResponse) {
        val user = response.profileObj.let { User(it.name, it.email, it.imageUrl) }

        store.dispatch(ChangeUser(user))
        props.enqueueSnackbar("Logged as ${user.name} <${user.email}>")
    }

    dialog(open = props.currentUser == null) {
        dialogTitle("Account required")
        dialogContent {
            dialogContentText("Sign-in with your Google account to book appointments")
        }
        dialogActions {
            googleLogin(
                // TODO move this to a build property
                clientId = "752118259594-201e8779d52re6d2lr2pkrca4fjt2tbj.apps.googleusercontent.com",
                buttonText = "Google Login",
                onSuccess = ::onLoggedIn,
                onFailure = {
                    console.error(it)
                    props.enqueueSnackbar(it.details ?: it.errorValue, variant = SnackbarVariant.ERROR)
                }
            )
        }
    }
}

fun RBuilder.loginDialog(currentUser: User?) = wrapped {
    attrs.currentUser = currentUser
}
