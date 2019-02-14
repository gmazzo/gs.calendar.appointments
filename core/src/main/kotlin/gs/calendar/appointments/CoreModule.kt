package gs.calendar.appointments

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.BasicAuthentication
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.calendar.Calendar
import dagger.Module
import dagger.Provides
import gs.calendar.appointments.agendas.AgendasModule
import gs.calendar.appointments.booking.BookingModule

@Module(includes = [AgendasModule::class, BookingModule::class])
internal class CoreModule {

    @Provides
    fun provideCalendar(transport: HttpTransport,
                        jsonFactory: JsonFactory,
                        requestInitializer: HttpRequestInitializer) =
            Calendar.Builder(transport, jsonFactory, requestInitializer)
                    .setApplicationName("Calendar Scheduler") // TODO make it a property
                    .build()

    @Provides
    fun provideHttpTransport(): HttpTransport = GoogleNetHttpTransport.newTrustedTransport()

    @Provides
    fun provideJsonFactory(): JsonFactory = JacksonFactory.getDefaultInstance()

    @Provides
    fun provideHttpRequestInitializer(): HttpRequestInitializer =
            BasicAuthentication("guillermo.mazzola@mercadolibre.cl", "") // TODO improve this

}
