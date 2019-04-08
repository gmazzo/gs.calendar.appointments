package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val avatar = module.Avatar.unsafeCast<Avatar>()

fun RBuilder.avatar(
    src: String,
    handler: (RHandler<Avatar.Props>) = {}
) = avatar.invoke {
    attrs.src = src

    handler(this)
}

external interface Avatar : RClass<Avatar.Props> {

    interface Props : RProps {

        var src: String

    }

}
