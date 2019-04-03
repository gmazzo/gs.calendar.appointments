package material_ui.core.styles

import react.RProps
import wrapWith
import kotlin.reflect.KClass

fun <T : Any> withTheme(component: KClass<T>) = component.wrapWith(module.withTheme())

external interface WithTheme : RProps {

    val theme: Theme

}
