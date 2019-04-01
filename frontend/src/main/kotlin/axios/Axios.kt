package axios

import kotlinext.js.jsObject
import kotlin.js.Promise

data class AxiosConfig(
    val params: dynamic = null
)

external interface AxiosResponse<T> {

    val data: T?

}

@JsModule("axios")
external object Axios {

    fun <T> get(
        url: String, config: AxiosConfig? = definedExternally
    ): Promise<AxiosResponse<T>>

    fun <T> post(
        url: String,
        data: dynamic = definedExternally,
        config: AxiosConfig? = definedExternally
    ): Promise<AxiosResponse<T>>

    fun <T> put(
        url: String,
        data: dynamic = definedExternally,
        config: AxiosConfig? = definedExternally
    ): Promise<AxiosResponse<T>>

    fun <T> delete(
        url: String, config: AxiosConfig? = definedExternally
    ): Promise<AxiosResponse<T>>

}

fun <T> Axios.get(url: String, paramsHandler: dynamic.() -> Unit) =
    get<T>(url, AxiosConfig(params = jsObject(paramsHandler)))

fun <T> Axios.post(url: String, data: Any, paramsHandler: dynamic.() -> Unit) =
    post<T>(url, data, AxiosConfig(params = jsObject(paramsHandler)))

fun <T> Axios.put(url: String, data: Any, paramsHandler: dynamic.() -> Unit) =
    put<T>(url, data, AxiosConfig(params = jsObject(paramsHandler)))

fun <T> Axios.delete(url: String, paramsHandler: dynamic.() -> Unit) =
    delete<T>(url, AxiosConfig(params = jsObject(paramsHandler)))
