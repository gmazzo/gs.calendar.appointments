package gs.calendar.appointments

import dagger.Component
import gs.calendar.appointments.agendas.AgendasService
import gs.calendar.appointments.booking.BookingService

@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun provideAgendasService(): AgendasService

    fun provideBookingService(): BookingService

}
