package gs.calendar.appointments

import gs.calendar.appointments.api.RequiredQueryParamValidator
import gs.calendar.appointments.api.agendas.AgendasController
import gs.calendar.appointments.api.auth.AuthController
import gs.calendar.appointments.api.booking.BookingController
import io.swagger.v3.jaxrs2.SwaggerSerializers
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource
import org.jboss.resteasy.plugins.interceptors.CorsFilter
import javax.inject.Inject

class Application : javax.ws.rs.core.Application() {

    @Inject
    lateinit var corsFilter: CorsFilter

    @Inject
    lateinit var authController: AuthController

    @Inject
    lateinit var agendasController: AgendasController

    @Inject
    lateinit var bookingController: BookingController

    init {
        DaggerApplicationComponent.builder()
            .coreComponent(
                DaggerCoreComponent.builder()
                    .applicationName("Appointments")
                    .clientSecrets(javaClass.getResource("/google_client_secrets.json"))
                    .build()
            )
            .build()
            .inject(this)
    }

    override fun getSingletons() = setOf(
        corsFilter,
        authController,
        agendasController,
        bookingController
    )

    override fun getClasses() = setOf(
        OpenApiResource::class.java,
        AcceptHeaderOpenApiResource::class.java,
        SwaggerSerializers::class.java,
        RequiredQueryParamValidator::class.java
    )

}
