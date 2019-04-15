package gs.calendar.appointments.slots

import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
internal interface SlotsModule {

    @Binds
    @Reusable
    fun bindSlotsService(impl: SlotsServiceImpl): SlotsService

}
