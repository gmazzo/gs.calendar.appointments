package gs.calendar.appointments.api

import javax.ws.rs.QueryParam
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.container.ResourceInfo
import javax.ws.rs.core.Context
import javax.ws.rs.ext.Provider

@Provider
class BooleanQueryParamSanitizer : ContainerRequestFilter {

    @Context
    private lateinit var resourceInfo: ResourceInfo

    override fun filter(context: ContainerRequestContext) {
        resourceInfo.resourceMethod.parameters
            .filter { it.type == Boolean::class.java }
            .map { it.getAnnotation(QueryParam::class.java) }
            .filter { it != null }
            .map(QueryParam::value)
            .map { context.uriInfo.queryParameters[it] }
            .filter { it != null && it.size == 1 && it[0] == "" }
            .forEach { it!![0] = true.toString() }
    }

}
