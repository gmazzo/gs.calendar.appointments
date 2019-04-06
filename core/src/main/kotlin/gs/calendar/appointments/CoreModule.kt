package gs.calendar.appointments

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.people.v1.PeopleService
import dagger.Module
import dagger.Provides
import gs.calendar.appointments.agendas.AgendasModule
import gs.calendar.appointments.auth.AuthModule
import gs.calendar.appointments.events.EventsModule
import javax.inject.Named

@Module(includes = [AuthModule::class, AgendasModule::class, EventsModule::class])
internal class CoreModule {

    @Provides
    fun provideCalendar(
        transport: HttpTransport,
        jsonFactory: JsonFactory,
        credential: Credential?,
        @Named("applicationName") applicationName: String
    ): Calendar =
        Calendar.Builder(transport, jsonFactory, credential)
            .setApplicationName(applicationName)
            .build()!!

    @Provides
    fun providePeopleService(
        transport: HttpTransport,
        jsonFactory: JsonFactory,
        credential: Credential?,
        @Named("applicationName") applicationName: String
    ): PeopleService =
        PeopleService.Builder(transport, jsonFactory, credential)
            .setApplicationName(applicationName)
            .build()!!

}
