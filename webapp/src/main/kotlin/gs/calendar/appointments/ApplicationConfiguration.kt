package gs.calendar.appointments

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.DataStoreFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.calendar.CalendarScopes
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.ApplicationScope
import java.io.File


@Component
@ApplicationScope
class ApplicationConfiguration {

    @Value("classpath:google_client_secrets.json")
    lateinit var clientSecrets: Resource

    @Bean
    fun jsonFactory(): JsonFactory =
            JacksonFactory.getDefaultInstance()

    @Bean
    fun httpTransport(): HttpTransport =
            GoogleNetHttpTransport.newTrustedTransport()

    @Bean
    fun dataStoreFactory(): DataStoreFactory =
            FileDataStoreFactory(File("build/data"))

    @Bean
    fun getClientCredential(): GoogleClientSecrets =
            GoogleClientSecrets.load(jsonFactory(), clientSecrets.inputStream.reader())

    @Bean
    fun googleAuthorizationCodeFlow(): GoogleAuthorizationCodeFlow = GoogleAuthorizationCodeFlow
            .Builder(
                    httpTransport(),
                    jsonFactory(),
                    getClientCredential(),
                    listOf(CalendarScopes.CALENDAR, CalendarScopes.CALENDAR_EVENTS))
            .setDataStoreFactory(dataStoreFactory())
            .setAccessType("offline")
            .build()

    @Bean
    fun credential(): Credential? =
            googleAuthorizationCodeFlow().loadCredential(PARAM_ADMIN_USER)

    companion object {

        const val PARAM_ADMIN_USER = "admin_user"

    }

}
