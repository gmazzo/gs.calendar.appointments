package gs.calendar.appointments.api

import gs.calendar.appointments.BuildConfig
import java.io.InputStream
import javax.inject.Singleton
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Singleton
@Path("/")
class StaticResource {

    @GET
    @Path("favicon.ico")
    @Produces("image/png")
    fun favIcon(): InputStream =
        javaClass.getResourceAsStream(BuildConfig.RESOURCE_FAVICON_ICO)

}
