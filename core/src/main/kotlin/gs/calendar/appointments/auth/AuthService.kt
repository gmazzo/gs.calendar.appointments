package gs.calendar.appointments.auth

import com.google.api.client.auth.oauth2.Credential
import java.net.URI

interface AuthService {

    fun authorize(callbackUri: URI): URI

    fun authorize(callbackUri: URI, authorizationCode: String): Token

    data class Token(
        val userId: String,
        internal var credential: Credential
    )

}
