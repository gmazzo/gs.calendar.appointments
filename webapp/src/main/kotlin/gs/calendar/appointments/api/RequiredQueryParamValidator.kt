package gs.calendar.appointments.api

import javax.ws.rs.QueryParam
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
            context.abortWith(
                Response
                    .status(Response.Status.BAD_REQUEST.statusCode)
                    .entity("Missing query params: ${missing.joinToString(", ")}")
                    .build()
            )
        }
    }

}