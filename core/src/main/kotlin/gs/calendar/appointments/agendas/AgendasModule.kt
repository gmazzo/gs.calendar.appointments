package gs.calendar.appointments.agendas

import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
internal interface AgendasModule {

    @Binds
    @Reusable
    fun bindAgendasService(impl: AgendasServiceImpl): AgendasService

}
