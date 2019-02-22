package gs.calendar.appointments.auth

import java.net.URI

interface AuthService {

    fun authorize(callbackUri: URI): URI

    fun authorize(callbackUri: URI, authorizationCode: String)

}
