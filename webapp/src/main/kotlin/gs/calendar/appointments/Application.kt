package gs.calendar.appointments

import com.google.api.client.util.store.FileDataStoreFactory
import gs.calendar.appointments.api.DefaultExceptionMapper
import gs.calendar.appointments.api.RequiredQueryParamValidator
import gs.calendar.appointments.api.agendas.AgendasResource
import gs.calendar.appointments.api.auth.AuthResource
import gs.calendar.appointments.api.auth.ForbiddenExceptionMapper
import gs.calendar.appointments.api.booking.BookingResource
import io.swagger.v3.jaxrs2.SwaggerSerializers
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource
import org.jboss.resteasy.plugins.interceptors.CorsFilter
import org.jboss.resteasy.plugins.interceptors.RoleBasedSecurityFeature
import javax.inject.Inject

class Application : javax.ws.rs.core.Application() {

    @Inject
    lateinit var corsFilter: CorsFilter

    @Inject
    lateinit var authResource: AuthResource

    @Inject
    lateinit var agendasResource: AgendasResource

    @Inject
    lateinit var bookingResource: BookingResource

    init {
        DaggerApplicationComponent.builder()
            .coreComponent(
                DaggerCoreComponent.builder()
                    .clientSecrets(javaClass.getResource("/$RESOURCE_GOOGLE_CLIENT_SECRETS_JSON"))
                    .dataStoreFactory(FileDataStoreFactory(DATA_STORE_FILE))
                    .build()
            )
            .build()
            .inject(this)
    }

    override fun getSingletons() = setOf(
        corsFilter,
        authResource,
        agendasResource,
        bookingResource
    )

    override fun getClasses() = setOf(
        // resources
        OpenApiResource::class.java,
        AcceptHeaderOpenApiResource::class.java,

        // features
        RoleBasedSecurityFeature::class.java,

        // providers
        SwaggerSerializers::class.java,
        DefaultExceptionMapper::class.java,
        ForbiddenExceptionMapper::class.java,
        RequiredQueryParamValidator::class.java
    )

}
