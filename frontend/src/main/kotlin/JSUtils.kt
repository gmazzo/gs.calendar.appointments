import kotlin.js.Date
import kotlin.js.Promise

val Date.sanitized
    get() = when (asDynamic()) {
        is Number -> Date(unsafeCast<Number>())
        is String -> Date(unsafeCast<String>())
        else -> this
    }

fun <T, S> Promise<T>.finally(onFinally: ((T) -> S)?): Promise<S> =
    asDynamic().finally(onFinally) as Promise<S>
