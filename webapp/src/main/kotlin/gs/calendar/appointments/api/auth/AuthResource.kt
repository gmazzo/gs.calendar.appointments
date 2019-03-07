package gs.calendar.appointments.api.auth

import gs.calendar.appointments.api.agendas.AgendasResource
import gs.calendar.appointments.auth.AuthService
import io.swagger.v3.oas.annotations.Operation
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriBuilder
import javax.ws.rs.core.UriInfo

@Singleton
@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
class AuthResource @Inject constructor(
    private val service: AuthService
) {

    @Context
    private lateinit var uri: UriInfo

    private val callbackUri by lazy {
        uri.requestUriBuilder
            .path(javaClass, "handleCallback")
            .build()
    }

    @GET
    fun authorize(): Response = Response
        .seeOther(service.authorize(callbackUri))
        .build()

    @GET
    @Path("handler")
    @Operation(hidden = true)
    fun handleCallback(@QueryParam("code") code: String): Response {
        service.authorize(callbackUri, code)

        return Response
            .seeOther(
                UriBuilder
                    .fromResource(AgendasResource::class.java)
                    .build()
            )
            .build()
    }

}
