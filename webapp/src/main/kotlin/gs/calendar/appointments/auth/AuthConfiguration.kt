package gs.calendar.appointments.auth

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.util.store.DataStoreFactory
import com.google.api.services.calendar.CalendarScopes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.ApplicationScope
import org.springframework.web.context.annotation.RequestScope

internal const val PARAM_ADMIN_USER = "admin_user"

@Component
@ApplicationScope
class AuthConfiguration {

    @Value("classpath:google_client_secrets.json")
    lateinit var clientSecrets: Resource

    @Autowired
    lateinit var jsonFactory: JsonFactory

    @Autowired
    lateinit var httpTransport: HttpTransport

    @Autowired
    lateinit var dataStoreFactory: DataStoreFactory

    @Bean
    fun getClientCredential(): GoogleClientSecrets =
            GoogleClientSecrets.load(jsonFactory, clientSecrets.inputStream.reader())

    @Bean
    fun googleAuthorizationCodeFlow(): GoogleAuthorizationCodeFlow = GoogleAuthorizationCodeFlow
            .Builder(
                    httpTransport,
                    jsonFactory,
                    getClientCredential(),
                    listOf(CalendarScopes.CALENDAR, CalendarScopes.CALENDAR_EVENTS))
            .setDataStoreFactory(dataStoreFactory)
            .setAccessType("offline")
            .build()

    @Bean
    @RequestScope
    fun credential(): Credential? =
            googleAuthorizationCodeFlow().loadCredential(PARAM_ADMIN_USER)

}
