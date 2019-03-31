import kotlinx.css.CSSBuilder
import kotlinx.css.RuleSet
import react.RElementBuilder
import react.dom.RDOMBuilder
import react.dom.jsStyle
import styled.toStyle
import kotlin.js.Promise

fun RElementBuilder<*>.css(handler: RuleSet) {
    attrs.asDynamic().style = CSSBuilder().apply(handler).toStyle()
}

fun RDOMBuilder<*>.css(handler: RuleSet) {
    attrs.jsStyle = CSSBuilder().apply(handler).toStyle()
}

fun <T, S> Promise<T>.finally(onFinally: ((T) -> S)?): Promise<S> =
    asDynamic().finally(onFinally) as Promise<S>
