package gs.calendar.appointments.events

import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
internal interface EventsModule {

    @Binds
    @Reusable
    fun bindBookingService(impl: EventsServiceImpl): EventsService

}
