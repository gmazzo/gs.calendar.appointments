package gs.calendar.appointments

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.people.v1.PeopleService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import gs.calendar.appointments.agendas.AgendasModule
import gs.calendar.appointments.auth.AuthModule
import gs.calendar.appointments.model.BuildConfig
import gs.calendar.appointments.slots.SlotsModule
import java.net.URL
import javax.inject.Named

@Module(includes = [AgendasModule::class, SlotsModule::class, AuthModule::class])
internal class CoreModule {

    @Provides
    @Reusable
    fun provideCredential(
        @Named("clientSecrets") clientSecrets: URL
    ): Credential = clientSecrets
        .openStream()
        .use { GoogleCredential.fromStream(it) }
        .createScoped(
            listOf(
                CalendarScopes.CALENDAR,
                CalendarScopes.CALENDAR_EVENTS
            )
        )

    @Provides
    @Reusable
    fun provideCalendar(
        transport: HttpTransport,
        jsonFactory: JsonFactory,
        credential: Credential,
        @Named("applicationName") applicationName: String
    ): Calendar =
        Calendar.Builder(transport, jsonFactory, credential)
            .setApplicationName(applicationName)
            .build()!!

    @Provides
    @Reusable
    fun providePeopleService(
        transport: HttpTransport,
        jsonFactory: JsonFactory,
        credential: Credential,
        @Named("applicationName") applicationName: String
    ): PeopleService =
        PeopleService.Builder(transport, jsonFactory, credential)
            .setApplicationName(applicationName)
            .build()!!

    @Provides
    @Reusable
    fun provideTokenVerifier(
        transport: HttpTransport,
        jsonFactory: JsonFactory
    ) = GoogleIdTokenVerifier.Builder(transport, jsonFactory)
        .setAudience(listOf(BuildConfig.API_CLIENT_ID))
        .build()

}
