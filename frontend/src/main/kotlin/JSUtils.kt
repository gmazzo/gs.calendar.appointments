import kotlinext.js.asJsObject
import kotlinext.js.jsObject
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import kotlin.js.Promise

fun <P : Promise<*>> P.finally(onFinally: () -> Unit): P =
    if (asJsObject().hasOwnProperty("finally")) asDynamic().finally(onFinally).unsafeCast<P>()
    else apply { then { onFinally() }; catch { onFinally() } }

fun <R : Response> Promise<R>.body() = then {
    if (it.ok) it.text() else throw IllegalStateException("${it.url} failed: ${it.status} ${it.statusText}")
}

fun <T : Any> request(method: String, serializer: SerializationStrategy<T>, body: T): RequestInit =
    jsObject {
        this.method = method
        this.headers = kotlinext.js.jsObject<dynamic> {
            this["Content-Type"] = "application/json"
        }
        this.body = Json.stringify(serializer, body)
    }
