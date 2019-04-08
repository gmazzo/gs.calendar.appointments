import kotlinext.js.asJsObject
import kotlin.js.Date
import kotlin.js.Promise

val Date.sanitized
    get() = when (asDynamic()) {
        is Number -> Date(unsafeCast<Number>())
        is String -> Date(unsafeCast<String>())
        else -> this
    }

fun <T : Promise<*>> T.finally(onFinally: () -> Unit): T =
    if (asJsObject().hasOwnProperty("finally")) asDynamic().finally(onFinally).unsafeCast<T>()
    else apply { then { onFinally() }; catch { onFinally() } }
