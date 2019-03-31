import react.RElementBuilder
import kotlin.js.Promise

var RElementBuilder<*>.jsStyle: dynamic
    get() {
        val value = attrs.asDynamic().style ?: kotlinext.js.js {}
        jsStyle = value
        return value
    }
    set(value) {
        attrs.asDynamic().style = value
    }

inline fun RElementBuilder<*>.jsStyle(handler: dynamic.() -> Unit) =
    handler(jsStyle)

fun <T, S> Promise<T>.finally(onFinally: ((T) -> S)?): Promise<S> =
    asDynamic().finally(onFinally) as Promise<S>
