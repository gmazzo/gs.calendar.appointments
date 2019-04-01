package gs.calendar.appointments

import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.jboss.resteasy.plugins.interceptors.CorsFilter

@Module
internal class ApplicationModule {

    @Provides
    @Reusable
    fun provideCorsFilter() = CorsFilter().apply {
        allowedOrigins.add("*")
    }

}
