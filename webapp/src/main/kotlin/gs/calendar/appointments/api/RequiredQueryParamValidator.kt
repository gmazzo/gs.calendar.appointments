package gs.calendar.appointments.api

import javax.ws.rs.QueryParam
import javax.ws.rs.WebApplicationException
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.container.ResourceInfo
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.ext.Provider

@Provider
class RequiredQueryParamValidator(
    @Context private val resourceInfo: ResourceInfo
) : ContainerRequestFilter {

    override fun filter(context: ContainerRequestContext) {
        val missing = resourceInfo.resourceMethod.parameters
            .filter { it.isAnnotationPresent(Required::class.java) }
            .map { it.getAnnotation(QueryParam::class.java)?.value }
            .filter { it != null && !context.uriInfo.queryParameters.containsKey(it) }
            .toSet()

        if (missing.isNotEmpty()) {
            throw WebApplicationException(
                Response
                    .status(Response.Status.BAD_REQUEST.statusCode)
                    .entity(
                        mapOf(
                            "requiredParams" to missing
                        )
                    )
                    .build()
            )
        }
    }

}