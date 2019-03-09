package gs.calendar.appointments

import io.undertow.Handlers.resource
import io.undertow.server.handlers.resource.ClassPathResourceManager
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer
import org.jboss.resteasy.util.PortProvider
import javax.ws.rs.ApplicationPath

fun main(args: Array<String>) {
    UndertowJaxrsServer().apply {
        deploy(Application::class.java)
        addResourcePrefixPath(
            "/",
            resource(ClassPathResourceManager(javaClass.classLoader, BuildConfig.RESOURCE_PUBLIC))
        )
        start()
    }

    printWelcomeMessage()
}

private fun printWelcomeMessage() {
    val appPath = Application::class.java
        .getAnnotation(ApplicationPath::class.java)
        ?.let { it.value + "/" }
        ?: ""

    val url = "http://${PortProvider.getHost()}:${PortProvider.getPort()}/${appPath}openapi.json"

    println("Visit https://petstore.swagger.io/?url=$url to explore the API")
}
