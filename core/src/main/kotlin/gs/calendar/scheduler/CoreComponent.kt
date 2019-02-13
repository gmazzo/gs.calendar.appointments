package gs.calendar.scheduler

import dagger.Component
import gs.calendar.scheduler.agendas.AgendasService
import gs.calendar.scheduler.booking.BookingService

@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun provideAgendasService(): AgendasService

    fun provideBookingService(): BookingService

}
