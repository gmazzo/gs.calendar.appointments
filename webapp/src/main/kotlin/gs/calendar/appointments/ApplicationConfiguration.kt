package gs.calendar.appointments

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.DataStoreFactory
import com.google.api.client.util.store.FileDataStoreFactory
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.ApplicationScope
import java.io.File


@Component
@ApplicationScope
class ApplicationConfiguration {

    @Bean
    fun jsonFactory(): JsonFactory =
            JacksonFactory.getDefaultInstance()

    @Bean
    fun httpTransport(): HttpTransport =
            GoogleNetHttpTransport.newTrustedTransport()

    @Bean
    fun dataStoreFactory(): DataStoreFactory =
            FileDataStoreFactory(File("build/data"))

}
