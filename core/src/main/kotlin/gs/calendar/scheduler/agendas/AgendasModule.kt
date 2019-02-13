package gs.calendar.scheduler.agendas

import dagger.Binds
import dagger.Module

@Module
internal interface AgendasModule {

    @Binds
    fun bindAgendasService(impl: AgendasServiceImpl): AgendasService

}
