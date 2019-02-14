package gs.calendar.appointments

import com.google.api.client.auth.oauth2.Credential
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.annotation.RequestScope

@Configuration
class CoreConfiguration {

    @Autowired
    var credential: Credential? = null

    @Bean
    @RequestScope
    fun coreComponent() = DaggerCoreComponent.builder()
            .applicationName("Appointments")
            .credential(credential)
            .build()

    @Bean
    @RequestScope
    fun agendasService() = coreComponent().provideAgendasService()

    @Bean
    @RequestScope
    fun bookingService() = coreComponent().provideBookingService()

}
