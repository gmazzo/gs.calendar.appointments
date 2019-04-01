package notistack

import kotlinext.js.jsObject
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RProps
import kotlin.reflect.KClass

private val withSnackbar = module.withSnackbar.unsafeCast<(Any) -> Any>()

fun <P : WithSnackbar, C : RComponent<P, *>> RBuilder.withSnackbar(kclazz: KClass<C>, handler: RHandler<P>) =
    child(withSnackbar(kclazz.js), jsObject {}, handler)

fun WithSnackbar.enqueueSnackbar(message: String, options: (WithSnackbar.Options.() -> Unit) = {}) =
    enqueueSnackbar(message, jsObject(options))

external interface WithSnackbar : RProps {

    val enqueueSnackbar: (message: String, options: Options?) -> String

    val closeSnackbar: (key: String) -> Unit

    interface Options {

        @JsName("variant")
        var variantValue: String

        var persist: Boolean

        var preventDuplicate: Boolean

        var autoHideDuration: Int

    }

}

enum class SnackbarVariant(val value: String) {
    DEFAULT("default"),
    ERROR("error"),
    SUCCESS("success"),
    WARNING("warning"),
    INFO("info")
}

var WithSnackbar.Options.variant
    get() = SnackbarVariant.valueOf(variantValue.toUpperCase())
    set(value) {
        variantValue = value.name.toLowerCase()
    }
