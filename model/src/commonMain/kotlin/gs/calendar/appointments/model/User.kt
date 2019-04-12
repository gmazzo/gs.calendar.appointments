package gs.calendar.appointments.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String? = null,
    val email: String,
    val imageUrl: String? = null,
    val tokenId: String? = null
) {

    fun isSelf(user: User?) = email == user?.email

}
