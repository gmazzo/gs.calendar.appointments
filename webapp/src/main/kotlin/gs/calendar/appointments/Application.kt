package gs.calendar.appointments

import com.google.api.client.util.store.FileDataStoreFactory
import gs.calendar.appointments.api.DefaultExceptionMapper
import gs.calendar.appointments.api.RequiredQueryParamValidator
import gs.calendar.appointments.api.agendas.AgendasResource
import gs.calendar.appointments.api.auth.AuthResource
import gs.calendar.appointments.api.booking.BookingResource
import io.swagger.v3.jaxrs2.SwaggerSerializers
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource
import org.jboss.resteasy.plugins.interceptors.CorsFilter
import java.io.File
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
                    .applicationName("Appointments")
                    .clientSecrets(javaClass.getResource("/google_client_secrets.json"))
                    .dataStoreFactory(FileDataStoreFactory(File("storage")))
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

        // providers
        SwaggerSerializers::class.java,
        DefaultExceptionMapper::class.java,
        RequiredQueryParamValidator::class.java
    )

}
