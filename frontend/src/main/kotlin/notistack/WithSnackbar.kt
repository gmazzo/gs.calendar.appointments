package notistack

import kotlinext.js.jsObject
import react.RProps
import kotlin.reflect.KClass

fun <T : Any> withSnackbar(component: KClass<T>) =
    module.withSnackbar(component.js).unsafeCast<JsClass<T>>().kotlin

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
