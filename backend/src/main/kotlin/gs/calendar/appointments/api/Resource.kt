package gs.calendar.appointments.api

import gs.calendar.appointments.model.BuildConfig
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.security.SecuritySchemes
import javax.inject.Singleton
import javax.ws.rs.Consumes
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecuritySchemes(
    SecurityScheme(
        name = "googleTokenIdAuth",
        type = SecuritySchemeType.APIKEY,
        `in` = SecuritySchemeIn.HEADER,
        paramName = BuildConfig.HEADER_AUTH_TOKEN_ID
    )
)
@SecurityRequirement(name = "googleTokenIdAuth")
abstract class Resource
