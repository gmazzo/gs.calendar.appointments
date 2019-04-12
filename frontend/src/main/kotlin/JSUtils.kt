import kotlinext.js.asJsObject
import kotlin.js.Promise

fun <P : Promise<*>> P.finally(onFinally: () -> Unit): P =
    if (asJsObject().hasOwnProperty("finally")) asDynamic().finally(onFinally).unsafeCast<P>()
    else apply { then { onFinally() }; catch { onFinally() } }
