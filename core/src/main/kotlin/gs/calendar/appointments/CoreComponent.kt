package gs.calendar.appointments

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.DataStoreFactory
import com.google.api.client.util.store.MemoryDataStoreFactory
import dagger.BindsInstance
import dagger.Component
import gs.calendar.appointments.agendas.AgendasService
import gs.calendar.appointments.auth.AuthService
import gs.calendar.appointments.events.EventsService
import java.net.URL
import javax.inject.Named

@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun provideAuthService(): AuthService

    fun provideAgendasService(): AgendasService

    fun provideEventsService(): EventsService

    @Component.Builder
    @Suppress("LeakingThis")
    abstract class Builder {

        init {
            applicationName("Appointments")
            httpTransport(GoogleNetHttpTransport.newTrustedTransport())
            jsonFactory(JacksonFactory.getDefaultInstance())
            dataStoreFactory(MemoryDataStoreFactory())
        }

        @BindsInstance
        abstract fun applicationName(@Named("applicationName") applicationName: String): Builder

        @BindsInstance
        abstract fun clientSecrets(@Named("clientSecrets") clientSecrets: URL): Builder

        @BindsInstance
        abstract fun httpTransport(httpTransport: HttpTransport): Builder

        @BindsInstance
        abstract fun jsonFactory(jsonFactory: JsonFactory): Builder

        @BindsInstance
        abstract fun dataStoreFactory(dataStoreFactory: DataStoreFactory): Builder

        abstract fun build(): CoreComponent

    }

}
