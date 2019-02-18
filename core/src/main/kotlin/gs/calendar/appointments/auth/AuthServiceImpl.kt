package gs.calendar.appointments.auth

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import java.net.URI
import javax.inject.Inject

internal class AuthServiceImpl @Inject constructor(
        private val flow: GoogleAuthorizationCodeFlow) : AuthService {

    override fun init(callbackUri: URI): URI = flow
            .newAuthorizationUrl()
            .setRedirectUri(callbackUri.toString())
            .toURI()

    override fun authorize(callbackUri: URI, code: String) {
        val token = flow.newTokenRequest(code)
                .setRedirectUri(callbackUri.toString())
                .execute()

        flow.createAndStoreCredential(token, PARAM_ADMIN_USER)
    }

}
