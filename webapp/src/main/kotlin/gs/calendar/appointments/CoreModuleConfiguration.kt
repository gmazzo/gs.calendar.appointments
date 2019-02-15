package gs.calendar.appointments

import com.google.api.client.auth.oauth2.Credential
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoreModuleConfiguration {

    @Autowired
    var credential: Credential? = null

    @Bean
    fun coreComponent() = DaggerCoreComponent.builder()
            .applicationName("Appointments")
            .credential(credential)
            .build()

    @Bean
    fun agendasService() = coreComponent().provideAgendasService()

    @Bean
    fun bookingService() = coreComponent().provideBookingService()

}
