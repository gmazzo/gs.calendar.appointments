package gs.calendar.appointments.booking

import dagger.Binds
import dagger.Module

@Module
internal interface BookingModule {

    @Binds
    fun bindBookingService(impl: BookingServiceImpl): BookingService

}
