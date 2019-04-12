package gs.calendar.appointments.api

import com.google.api.client.http.HttpResponseException
import io.undertow.util.Headers
import kotlinx.serialization.Serializable
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class DefaultExceptionMapper : ExceptionMapper<Exception> {

    override fun toResponse(exception: Exception): Response = Response
        .status(exception.statusCode)
        .entity(Error(exception.statusCode, exception.message))
        .header(Headers.CONTENT_TYPE_STRING, MediaType.APPLICATION_JSON)
        .build()
        .also { exception.printStackTrace() }

    private val Exception.statusCode
        get() =
            if (this is HttpResponseException) this.statusCode
            else Response.Status.INTERNAL_SERVER_ERROR.statusCode

    @Serializable
    data class Error(
        val statusCode: Int,
        val message: String? = null
    )

}
