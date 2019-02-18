package gs.calendar.appointments

import gs.calendar.appointments.agendas.AgendasController
import gs.calendar.appointments.auth.AuthController
import gs.calendar.appointments.booking.BookingController
import javax.inject.Inject

class Application : javax.ws.rs.core.Application() {

    @Inject
    lateinit var authController: AuthController

    @Inject
    lateinit var agendasController: AgendasController

    @Inject
    lateinit var bookingController: BookingController

    private val injector by lazy {
        DaggerApplicationComponent.builder()
                .coreComponent(DaggerCoreComponent.builder()
                        .applicationName("Appointments")
                        .clientSecrets(javaClass.getResource("/google_client_secrets.json"))
                        .build())
                .build()
    }

    override fun getSingletons(): Set<*> {
        injector.inject(this)

        return setOf(authController, agendasController, bookingController)
    }

}
