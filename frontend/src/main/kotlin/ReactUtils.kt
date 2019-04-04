import kotlinx.css.CSSBuilder
import kotlinx.css.RuleSet
import notistack.SnackbarVariant
import notistack.WithSnackbar
import notistack.enqueueSnackbar
import org.w3c.dom.events.MouseEvent
import react.HOC
import react.RClass
import react.RComponent
import react.RElementBuilder
import react.RProps
import react.dom.RDOMBuilder
import react.dom.jsStyle
import react.invoke
import styled.toStyle
import kotlin.js.Promise
import kotlin.reflect.KClass

val <P : RProps, T : RComponent<P, *>> KClass<T>.rClass get() = js.unsafeCast<RClass<P>>()

fun <P : RProps> allOf(vararg hocs: HOC<P, P>): (component: RClass<P>) -> RClass<P> =
    { hocs.fold(it) { acc, hoc -> hoc.invoke(acc) } }

fun RElementBuilder<*>.css(handler: RuleSet) {
    attrs.asDynamic().style = CSSBuilder().apply(handler).toStyle()
}

fun RDOMBuilder<*>.css(handler: RuleSet) {
    attrs.jsStyle = CSSBuilder().apply(handler).toStyle()
}

fun RElementBuilder<*>.onClick(onClick: (MouseEvent) -> Unit) {
    attrs.asDynamic().onClick = onClick
}

fun <T, S> Promise<T>.finally(onFinally: ((T) -> S)?): Promise<S> =
    asDynamic().finally(onFinally) as Promise<S>

fun Throwable.snackbar(props: WithSnackbar) {
    console.log(this)

    props.enqueueSnackbar(toString(), variant = SnackbarVariant.ERROR)
}
