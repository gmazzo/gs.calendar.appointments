package gs.calendar.appointments

import com.google.api.client.auth.oauth2.Credential
import dagger.BindsInstance
import dagger.Component
import gs.calendar.appointments.agendas.AgendasService
import gs.calendar.appointments.booking.BookingService
import javax.inject.Named

@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun provideAgendasService(): AgendasService

    fun provideBookingService(): BookingService

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationName(@Named("applicationName") applicationName: String): Builder

        @BindsInstance
        fun credential(credential: Credential?): Builder

        fun build(): CoreComponent

    }

}
