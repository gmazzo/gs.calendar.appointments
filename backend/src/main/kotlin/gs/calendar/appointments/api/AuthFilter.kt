package gs.calendar.appointments.api

import gs.calendar.appointments.auth.AuthService
import gs.calendar.appointments.model.BuildConfig
import javax.inject.Inject
import javax.ws.rs.ForbiddenException
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter

class AuthFilter @Inject constructor(
    private val service: AuthService
) : ContainerRequestFilter {

    override fun filter(context: ContainerRequestContext) {
        context.headers[BuildConfig.HTTP_HEADER_AUTH_TOKEN_ID]?.let {
            val accessToken = it.first()
            val authUser = service.verify(accessToken) ?: throw ForbiddenException("Invalid tokenId: $accessToken")

            context.setProperty(PARAM_AUTH_USER, authUser)
        }
    }

}
