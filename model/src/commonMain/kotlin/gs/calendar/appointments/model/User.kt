package gs.calendar.appointments.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String? = null,
    val email: String,
    val imageUrl: String? = null
) {

    override fun hashCode() = email.hashCode()

    override fun equals(other: Any?) = other is User && email == other.email

}
