package gs.calendar.appointments.auth

import java.net.URI

interface AuthService {

    fun init(callbackUri: URI): URI

    fun authorize(callbackUri: URI, code: String)

}
