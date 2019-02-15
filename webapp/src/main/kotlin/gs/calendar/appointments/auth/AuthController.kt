package gs.calendar.appointments.auth

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("auth")
class AuthController(
        val flow: GoogleAuthorizationCodeFlow) {

    @GetMapping
    fun authorize(response: HttpServletResponse) {
        response.sendRedirect(flow.newAuthorizationUrl()
                .setRedirectUri(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/handler")
                        .build()
                        .toUriString())
                .build())
    }

    @GetMapping(path = ["handler"])
    fun handleCallback(@RequestParam("code") code: String, response: HttpServletResponse) {
        val token = flow.newTokenRequest(code)
                .setRedirectUri(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .build()
                        .toUriString())
                .execute()

        flow.createAndStoreCredential(token, PARAM_ADMIN_USER)

        response.sendRedirect("/agendas") // TODO improve this
    }

}
