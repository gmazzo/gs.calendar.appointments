package gs.calendar.appointments.frontend.imports

import kotlin.js.Promise

data class AxiosConfig(
    val params: Map<String, Any>? = null
)

@JsModule("axios")
external object axios {

    fun <T> get(url: String, config: AxiosConfig? = definedExternally): Promise<T>

    fun <T> post(url: String, data: Any? = definedExternally, config: AxiosConfig? = definedExternally): Promise<T>

    fun <T> put(url: String, data: Any? = definedExternally, config: AxiosConfig? = definedExternally): Promise<T>

    fun <T> delete(url: String, config: AxiosConfig? = definedExternally): Promise<T>

}
