package gs.calendar.appointments

import io.undertow.jsp.HackInstanceManager
import io.undertow.jsp.JspServletBuilder
import io.undertow.server.handlers.resource.ClassPathResourceManager
import io.undertow.servlet.api.DeploymentInfo
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer
import org.jboss.resteasy.util.PortProvider
import java.io.File
import javax.ws.rs.ApplicationPath

fun main(args: Array<String>) {
    UndertowJaxrsServer().apply {
        deploy(Application::class.java)
        deploy(
            DeploymentInfo()
                .setDeploymentName(BuildConfig.APP_NAME)
                .setContextPath("/")
                .setClassLoader(javaClass.classLoader)
                .setResourceManager(ClassPathResourceManager(javaClass.classLoader, BuildConfig.RESOURCE_PUBLIC))
                .addWelcomePage(File(BuildConfig.RESOURCE_PUBLIC_INDEX_JSP).relativeTo(File(BuildConfig.RESOURCE_PUBLIC)).path)
                .apply { JspServletBuilder.setupDeployment(this, hashMapOf(), hashMapOf(), HackInstanceManager()) }
                .addServlet(JspServletBuilder.createServlet("JSP Servlet", "*.jsp"))
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
