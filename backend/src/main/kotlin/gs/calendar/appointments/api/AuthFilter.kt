package gs.calendar.appointments.api

import gs.calendar.appointments.auth.AuthService
import gs.calendar.appointments.model.BuildConfig
import gs.calendar.appointments.model.User
import org.jboss.resteasy.core.ResteasyContext
import javax.inject.Inject
import javax.ws.rs.ForbiddenException
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.ext.Provider

@Provider
class AuthFilter @Inject constructor(
    private val service: AuthService
) : ContainerRequestFilter {

    override fun filter(context: ContainerRequestContext) {
        context.headers[BuildConfig.HEADER_AUTH_TOKEN_ID]?.let {
            val accessToken = it.first()
            val authUser = service.verify(accessToken) ?: throw ForbiddenException("Invalid tokenId: $accessToken")

            ResteasyContext.pushContext(User::class.java, authUser)
        }
    }

}
