package gs.calendar.appointments

import dagger.BindsInstance
import dagger.Component
import gs.calendar.appointments.agendas.AgendasService
import gs.calendar.appointments.auth.AuthService
import gs.calendar.appointments.booking.BookingService
import java.net.URL
import javax.inject.Named

@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun provideAuthService(): AuthService

    fun provideAgendasService(): AgendasService

    fun provideBookingService(): BookingService

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationName(@Named("applicationName") applicationName: String): Builder

        @BindsInstance
        fun clientSecrets(@Named("clientSecrets") clientSecrets: URL): Builder

        fun build(): CoreComponent

    }

}
