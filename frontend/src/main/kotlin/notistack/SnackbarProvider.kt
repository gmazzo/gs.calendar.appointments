package notistack

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

val snackbarProvider = module.SnackbarProvider.unsafeCast<SnackbarProvider>()

fun RBuilder.snackbarProvider(
    maxSnack: Int = 3,
    handler: (RHandler<SnackbarProvider.Props>) = {}
) = snackbarProvider.invoke {
    attrs.maxSnack = maxSnack
    handler(this)
}

external interface SnackbarProvider : RClass<SnackbarProvider.Props> {

    interface Props : RProps {

        var maxSnack: Int

    }

}
