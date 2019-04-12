package gs.calendar.appointments.auth

import gs.calendar.appointments.model.User

interface AuthService {

    fun verify(tokenId: String): User?

}
