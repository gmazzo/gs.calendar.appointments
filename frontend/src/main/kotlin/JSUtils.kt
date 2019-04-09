import kotlinext.js.asJsObject
import org.w3c.fetch.Response
import kotlin.js.Promise

fun <P : Promise<*>> P.finally(onFinally: () -> Unit): P =
    if (asJsObject().hasOwnProperty("finally")) asDynamic().finally(onFinally).unsafeCast<P>()
    else apply { then { onFinally() }; catch { onFinally() } }

fun <R : Response> Promise<R>.body() = then {
    if (it.ok) it.text() else throw IllegalStateException("${it.url} failed: ${it.status} ${it.statusText}")
}
