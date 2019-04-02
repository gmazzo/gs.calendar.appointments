package material_ui.core.styles

import kotlin.reflect.KClass

fun <T : Any> withTheme(component: KClass<T>) =
    module.withTheme()(component.js).unsafeCast<JsClass<T>>().kotlin

external interface WithTheme {

    val theme: Theme

}

external interface Theme {

    val spacing: Spacing

    interface Spacing {
        val unit: Int
    }

}
