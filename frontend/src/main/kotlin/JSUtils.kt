import kotlinext.js.asJsObject
import kotlin.js.Promise

fun <T : Promise<*>> T.finally(onFinally: () -> Unit): T =
    if (asJsObject().hasOwnProperty("finally")) asDynamic().finally(onFinally).unsafeCast<T>()
    else apply { then { onFinally() }; catch { onFinally() } }
