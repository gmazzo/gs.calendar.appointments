package gs.calendar.appointments.api.auth

import javax.ws.rs.ForbiddenException
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriBuilder
import javax.ws.rs.core.UriInfo
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ForbiddenExceptionMapper : ExceptionMapper<ForbiddenException> {

    @Context
    lateinit var uriInfo: UriInfo


    override fun toResponse(exception: ForbiddenException): Response = Response
        .seeOther(
            UriBuilder
                .fromResource(AuthResource::class.java)
                .build()
        )
        .build()

}
