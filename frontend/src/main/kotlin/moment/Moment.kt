package moment

import react.RClass
import react.RProps

external interface Moment : RClass<RProps>

@JsModule("moment")
external val moment: Moment
