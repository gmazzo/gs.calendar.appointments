package gs.calendar.appointments.frontend.imports

import kotlin.js.Promise

data class AxiosConfig(
    val params: Map<String, Any>? = null
)

external interface AxiosResponse<T> {

    val data: T?

}

@JsModule("axios")
external interface Axios {

    fun <T> get(
        url: String, config: AxiosConfig? = definedExternally
    ): Promise<AxiosResponse<T>>

    fun <T> post(
        url: String,
        data: Any? = definedExternally,
        config: AxiosConfig? = definedExternally
    ): Promise<AxiosResponse<T>>

    fun <T> put(
        url: String,
        data: Any? = definedExternally,
        config: AxiosConfig? = definedExternally
    ): Promise<AxiosResponse<T>>

    fun <T> delete(
        url: String, config: AxiosConfig? = definedExternally
    ): Promise<AxiosResponse<T>>

}

@JsModule("axios")
external val axios: Axios
