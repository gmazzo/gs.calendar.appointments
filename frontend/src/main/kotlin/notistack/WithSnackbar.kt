package notistack

import kotlinext.js.jsObject
import react.HOC
import react.RProps

fun <P : WithSnackbar> withSnackbar() = module.withSnackbar.unsafeCast<HOC<P, P>>()

fun WithSnackbar.enqueueSnackbar(
    message: String,
    variant: SnackbarVariant? = null,
    persist: Boolean? = null,
    preventDuplicate: Boolean? = null,
    autoHideDuration: Int? = null,
    options: (WithSnackbar.Options.() -> Unit) = {}
) =
    enqueueSnackbar(message, jsObject {
        variant?.let { this.variant = it }
        persist?.let { this.persist = it }
        preventDuplicate?.let { this.preventDuplicate = it }
        autoHideDuration?.let { this.autoHideDuration = it }
        options(this)
    })

external interface WithSnackbar : RProps {

    fun enqueueSnackbar(message: String, options: Options?): SnackbarKey

    fun closeSnackbar(key: SnackbarKey)

    interface Options {

        @JsName("variant")
        var variantValue: String

        var persist: Boolean

        var preventDuplicate: Boolean

        var autoHideDuration: Int

    }

}

typealias SnackbarKey = String

enum class SnackbarVariant(val value: String) {
    DEFAULT("default"),
    ERROR("error"),
    SUCCESS("success"),
    WARNING("warning"),
    INFO("info")
}

var WithSnackbar.Options.variant
    get() = variantValue.let { SnackbarVariant.values().find { v -> v.value == it }!! }
    set(value) {
        variantValue = value.value
    }
