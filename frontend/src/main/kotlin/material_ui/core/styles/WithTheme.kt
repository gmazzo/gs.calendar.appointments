package material_ui.core.styles

import react.HOC
import react.RProps

fun <P : WithTheme> withTheme() = module.withTheme().unsafeCast<HOC<P, P>>()

external interface WithTheme : RProps {

    val theme: Theme

}
