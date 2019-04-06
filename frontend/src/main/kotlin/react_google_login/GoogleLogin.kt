package react_google_login

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val googleLogin = module.GoogleLogin.unsafeCast<GoogleLogin>()

fun RBuilder.googleLogin(
    clientId: String,
    scope: String? = null,
    accessType: GoogleLoginAccessType = GoogleLoginAccessType.ONLINE,
    buttonText: String,
    onRequest: (() -> Unit)? = null,
    onSuccess: (response: GoogleLogin.SuccessResponse) -> Unit,
    onFailure: (response: GoogleLogin.FailureResponse) -> Unit,
    isSignedIn: Boolean? = null,
    handler: (RHandler<GoogleLogin.Props>) = {}
) = googleLogin {
    attrs.clientId = clientId
    scope?.let { attrs.scope = it }
    attrs.accessType = accessType
    attrs.buttonText = buttonText
    onRequest?.let { attrs.onRequest = it }
    attrs.onSuccess = onSuccess
    attrs.onFailure = onFailure
    isSignedIn?.let { attrs.isSignedIn = it }
    attrs.asDynamic().cookiePolicy = "single_host_origin"

    handler(this)
}

abstract external class GoogleLogin : RClass<GoogleLogin.Props> {

    interface Props : RProps {

        var clientId: String

        var scope: String

        @JsName("accessType")
        var accessTypeValue: String

        var buttonText: String

        var onRequest: () -> Unit

        var onSuccess: (response: SuccessResponse) -> Unit

        var onFailure: (response: FailureResponse) -> Unit

        var isSignedIn: Boolean

    }

    interface SuccessResponse {

        val googleId: String

        val tokenId: String

        val accessToken: String

        val tokenObj: dynamic

        val profileObj: Profile

        @JsName("code")
        val offlineCode: String

    }

    interface Profile {
        val googleId: String
        val name: String
        val email: String
        val imageUrl: String
    }

    interface FailureResponse {

        @JsName("error")
        val errorValue: String

        val details: String?

    }

}

enum class GoogleLoginAccessType(val value: String) {
    ONLINE("online"),
    OFFLINE("offline")
}

enum class GoogleLoginError(val value: String) {
    IDPIFRAME_INITIALIZATION_FAILED("idpiframe_initialization_failed"),
    POPUP_CLOSED_BY_USER("popup_closed_by_user"),
    ACCESS_DENIED("access_denied"),
    IMMEDIATE_FAILED("immediate_failed")
}

var GoogleLogin.Props.accessType
    get() = accessTypeValue.let { GoogleLoginAccessType.values().find { v -> v.value == it }!! }
    set(value) {
        accessTypeValue = value.value
    }

val GoogleLogin.FailureResponse.error
    get() = GoogleLoginError.values().find { it.value == errorValue }!!
