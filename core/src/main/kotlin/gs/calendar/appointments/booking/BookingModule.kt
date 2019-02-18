package gs.calendar.appointments.booking

import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
internal interface BookingModule {

    @Binds
    @Reusable
    fun bindBookingService(impl: BookingServiceImpl): BookingService

}
