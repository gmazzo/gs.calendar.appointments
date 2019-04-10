package gs.calendar.appointments.frontend

import gs.calendar.appointments.model.User
import material_ui.core.avatar
import material_ui.icons.Icons
import material_ui.icons.icon
import react.RBuilder

fun RBuilder.userAvatar(user: User) = avatar(src = user.imageUrl) {
    if (user.name.isNullOrBlank()) {
        icon(icon = Icons.ACCOUNT_CIRCLE)

    } else {
        +user.name!!.replace("(\\w)\\w+\\W*".toRegex()) { it.groups[1]!!.value.toUpperCase() }
    }
}
