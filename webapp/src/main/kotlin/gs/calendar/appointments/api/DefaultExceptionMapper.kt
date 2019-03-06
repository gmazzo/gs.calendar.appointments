package gs.calendar.appointments.api

import com.google.api.client.http.HttpResponseException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class DefaultExceptionMapper : ExceptionMapper<Exception> {

    override fun toResponse(exception: Exception): Response = Response
        .status(exception.statusCode)
        .entity(exception.message)
        .build()
        .also { exception.printStackTrace() }

    private val Exception.statusCode
        get() =
            if (this is HttpResponseException) this.statusCode
            else Response.Status.INTERNAL_SERVER_ERROR.statusCode

}
