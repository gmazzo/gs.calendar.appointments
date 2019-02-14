package gs.calendar.appointments.agendas

import dagger.Binds
import dagger.Module

@Module
internal interface AgendasModule {

    @Binds
    fun bindAgendasService(impl: AgendasServiceImpl): AgendasService

}
