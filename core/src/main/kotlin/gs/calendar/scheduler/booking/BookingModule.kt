package gs.calendar.scheduler.booking

import dagger.Binds
import dagger.Module

@Module
internal interface BookingModule {

    @Binds
    fun bindBookingService(impl: BookingServiceImpl): BookingService

}
