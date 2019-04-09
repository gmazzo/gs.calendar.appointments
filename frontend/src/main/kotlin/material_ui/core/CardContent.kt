package material_ui.core

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val cardContent = module.CardContent.unsafeCast<CardContent>()

fun RBuilder.cardContent(
    handler: (RHandler<Card.Props>) = {}
) = cardContent.invoke(handler)

abstract external class CardContent : RClass<RProps>
