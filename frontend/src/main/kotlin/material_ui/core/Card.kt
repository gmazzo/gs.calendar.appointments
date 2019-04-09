package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val card = module.Card.unsafeCast<Card>()

fun RBuilder.card(
    raised: Boolean? = null,
    handler: (RHandler<Card.Props>) = {}
) = card.invoke {
    raised?.let { attrs.raised = it }

    handler(this)
}

abstract external class Card : RClass<Card.Props> {

    interface Props : RProps {
        var raised: Boolean
    }

}
