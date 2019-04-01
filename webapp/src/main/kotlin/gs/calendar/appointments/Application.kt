package gs.calendar.appointments

import com.google.api.client.util.store.FileDataStoreFactory
import gs.calendar.appointments.api.DefaultExceptionMapper
import gs.calendar.appointments.api.agendas.AgendasResource
import gs.calendar.appointments.api.auth.AuthResource
import gs.calendar.appointments.api.slots.SlotsResource
import io.swagger.v3.jaxrs2.SwaggerSerializers
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource
import org.jboss.resteasy.plugins.interceptors.CorsFilter
import javax.inject.Inject
import javax.ws.rs.ApplicationPath

@ApplicationPath(BuildConfig.API_CONTEXT)
class Application : javax.ws.rs.core.Application() {

    @Inject
    lateinit var corsFilter: CorsFilter

    @Inject
    lateinit var authResource: AuthResource

    @Inject
    lateinit var agendasResource: AgendasResource

    @Inject
    lateinit var slotsResource: SlotsResource

    init {
        DaggerApplicationComponent.builder()
            .coreComponent(
                DaggerCoreComponent.builder()
                    .adminUserId(BuildConfig.ADMIN_USER_ID)
                    .clientSecrets(javaClass.getResource("/" + Resources.GOOGLE_CLIENT_SECRETS_JSON))
                    .dataStoreFactory(FileDataStoreFactory(BuildConfig.DATA_STORE_FILE))
                    .build()
            )
            .build()
            .inject(this)
    }

    override fun getSingletons() = setOf(
        corsFilter,
        authResource,
        agendasResource,
        slotsResource
    )

    override fun getClasses() = setOf(
        // resources
        OpenApiResource::class.java,
        AcceptHeaderOpenApiResource::class.java,

        // providers
        SwaggerSerializers::class.java,
        DefaultExceptionMapper::class.java
    )

}
