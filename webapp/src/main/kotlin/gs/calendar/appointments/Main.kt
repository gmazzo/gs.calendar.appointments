package gs.calendar.appointments

import org.jboss.resteasy.core.ResteasyDeploymentImpl
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer

fun main(args: Array<String>) {
    NettyJaxrsServer().apply {
        deployment = ResteasyDeploymentImpl().apply {
            application = Application()
        }
        start()

        println("Visit https://petstore.swagger.io/?url=http://localhost:$port/openapi.json to explore the API")
    }
}
