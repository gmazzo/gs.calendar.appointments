package gs.calendar.appointments

import io.undertow.Undertow
import io.undertow.server.handlers.resource.ClassPathResourceManager
import io.undertow.servlet.api.DeploymentInfo
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer
import org.jboss.resteasy.util.PortProvider
import java.io.File
import javax.ws.rs.ApplicationPath

fun main() = startServer(null, null)

fun startServer(staticContent: String?, welcomePage: String?) {
    UndertowJaxrsServer().apply {
        deploy(Application::class.java)
        deploy(
            DeploymentInfo()
                .setDeploymentName(BuildConfig.APP_NAME)
                .setContextPath("/")
                .setClassLoader(javaClass.classLoader)
                .apply {
                    if (staticContent != null) {
                        resourceManager = ClassPathResourceManager(javaClass.classLoader, staticContent)

                        if (welcomePage != null) {
                            addWelcomePage(File(welcomePage).relativeTo(File(staticContent)).path)
                        }
                    }
                }
        )
        start(Undertow.builder().addHttpListener(PortProvider.getPort(), "0.0.0.0"))
    }

    printWelcomeMessage(welcomePage != null)
}

private fun printWelcomeMessage(hasFrontend: Boolean) {
    val appPath = Application::class.java
        .getAnnotation(ApplicationPath::class.java)
        ?.let { it.value + "/" }
        ?: ""

    val url = "http://${PortProvider.getHost()}:${PortProvider.getPort()}"

    if (hasFrontend) {
        println("Visit $url to open the App")
    }
    println("Visit https://petstore.swagger.io/?url=$url/${appPath}openapi.json to explore the API")
}
