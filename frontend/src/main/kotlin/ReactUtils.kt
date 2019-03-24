import react.RElementBuilder

var RElementBuilder<*>.jsStyle: dynamic
    get() {
        val value = attrs.asDynamic().style ?: kotlinext.js.js {}
        jsStyle = value
        return value
    }
    set(value) {
        attrs.asDynamic().style = value
    }

inline fun RElementBuilder<*>.jsStyle(handler: dynamic.() -> Unit) =
    handler(jsStyle)
