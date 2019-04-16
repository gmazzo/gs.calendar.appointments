package gs.calendar.appointments

import com.google.api.client.util.store.FileDataStoreFactory
import com.jakewharton.rs.kotlinx.serialization.asMessageBodyReader
import com.jakewharton.rs.kotlinx.serialization.asMessageBodyWriter
import gs.calendar.appointments.api.AuthFilter
import gs.calendar.appointments.api.BooleanQueryParamSanitizer
import gs.calendar.appointments.api.DefaultExceptionMapper
import gs.calendar.appointments.api.agendas.AgendasResource
import gs.calendar.appointments.api.slots.SlotsResource
import gs.calendar.appointments.backend.BuildConfig
import gs.calendar.appointments.backend.Resources
import io.swagger.v3.jaxrs2.SwaggerSerializers
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.jboss.resteasy.plugins.interceptors.CorsFilter
import javax.inject.Inject
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.MediaType
import gs.calendar.appointments.model.BuildConfig as ModelBuildConfig

@ApplicationPath(BuildConfig.API_CONTEXT)
class Application : javax.ws.rs.core.Application() {

    @Inject
    lateinit var corsFilter: CorsFilter

    @Inject
    lateinit var authFilter: AuthFilter

    @Inject
    lateinit var agendasResource: AgendasResource

    @Inject
    lateinit var slotsResource: SlotsResource

    init {
        DaggerApplicationComponent.builder()
            .coreComponent(
                DaggerCoreComponent.builder()
                    .clientSecrets(javaClass.getResource("/" + Resources.GOOGLE_CLIENT_SECRETS_JSON))
                    .dataStoreFactory(FileDataStoreFactory(BuildConfig.DATA_STORE_FILE))
                    .build()
            )
            .build()
            .inject(this)
    }

    override fun getSingletons() = setOf(
        *Json(JsonConfiguration(encodeDefaults = false)).let {
            arrayOf(
                it.asMessageBodyReader(MediaType.APPLICATION_JSON_TYPE),
                it.asMessageBodyWriter(MediaType.APPLICATION_JSON_TYPE)
            )
        },
        corsFilter,
        authFilter,
        agendasResource,
        slotsResource
    )

    override fun getClasses() = setOf(
        // swagger
        OpenApiResource::class.java,
        AcceptHeaderOpenApiResource::class.java,
        SwaggerSerializers::class.java,

        // providers
        BooleanQueryParamSanitizer::class.java,
        DefaultExceptionMapper::class.java
    )

}
