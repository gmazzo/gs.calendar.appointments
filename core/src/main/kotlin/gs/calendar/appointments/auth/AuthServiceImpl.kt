package gs.calendar.appointments.auth

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import gs.calendar.appointments.model.User
import javax.inject.Inject

internal class AuthServiceImpl @Inject constructor(
    private val verifier: GoogleIdTokenVerifier
) : AuthService {

    override fun verify(tokenId: String) = verifier
        .verify(tokenId)
        ?.asUser()

    private fun GoogleIdToken.asUser() = with(payload) {
        User(
            name = this["name"] as String?,
            email = email,
            imageUrl = this["picture"] as String?
        )
    }

}
