package gs.calendar.appointments.api

import kotlinx.serialization.Serializable
import javax.ws.rs.NotAcceptableException
import javax.ws.rs.Produces
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
@Produces(MediaType.APPLICATION_JSON)
class DefaultExceptionMapper : ExceptionMapper<Exception> {

    override fun toResponse(exception: Exception): Response = when (exception) {
        is NotAcceptableException -> exception.response
        is WebApplicationException -> Response.status(exception.response.statusInfo)
            .entity(ErrorMessage(exception.response.status, exception.localizedMessage))
            .build()
        else -> Response.serverError()
            .entity(ErrorMessage(Response.Status.INTERNAL_SERVER_ERROR.statusCode, exception.localizedMessage))
            .build()
    }.also { exception.printStackTrace() }

    @Serializable
    data class ErrorMessage(val statusCode: Int, val message: String)

}
