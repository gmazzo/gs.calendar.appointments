package gs.calendar.appointments

import gs.calendar.appointments.api.RequiredQueryParamValidator
import gs.calendar.appointments.api.agendas.AgendasController
import gs.calendar.appointments.api.auth.AuthController
import gs.calendar.appointments.api.booking.BookingController
import javax.inject.Inject

class Application : javax.ws.rs.core.Application() {

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

    override fun getSingletons() =
        setOf(authController, agendasController, bookingController)

    override fun getClasses() =
        setOf(RequiredQueryParamValidator::class.java)

}
