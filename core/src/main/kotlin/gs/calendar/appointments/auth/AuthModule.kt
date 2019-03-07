package gs.calendar.appointments.auth

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.util.store.DataStoreFactory
import com.google.api.services.calendar.CalendarScopes
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import java.net.URL
import javax.inject.Named

@Module(includes = [AuthModule.Bindings::class])
internal class AuthModule {

    @Provides
    @Reusable
    fun provideClientCredential(
        jsonFactory: JsonFactory,
        @Named("clientSecrets") clientSecrets: URL
    ): GoogleClientSecrets =
        GoogleClientSecrets.load(jsonFactory, clientSecrets.openStream().reader())

    @Provides
    @Reusable
    fun provideGoogleAuthorizationCodeFlow(
        jsonFactory: JsonFactory,
        httpTransport: HttpTransport,
        clientCredential: GoogleClientSecrets,
        dataStoreFactory: DataStoreFactory
    ): GoogleAuthorizationCodeFlow =
        GoogleAuthorizationCodeFlow
            .Builder(
                httpTransport,
                jsonFactory,
                clientCredential,
                listOf(
                    "https://www.googleapis.com/auth/userinfo.email",
                    CalendarScopes.CALENDAR,
                    CalendarScopes.CALENDAR_EVENTS
                )
            )
            .setDataStoreFactory(dataStoreFactory)
            .setAccessType("offline")
            .build()

    @Provides
    fun provideCredential(
        flow: GoogleAuthorizationCodeFlow,
        @Named("adminUserId") adminUserId: String
    ): Credential? =
        flow.loadCredential(adminUserId)

    @Module
    interface Bindings {

        @Binds
        @Reusable
        fun bindAuthService(impl: AuthServiceImpl): AuthService

    }

}
