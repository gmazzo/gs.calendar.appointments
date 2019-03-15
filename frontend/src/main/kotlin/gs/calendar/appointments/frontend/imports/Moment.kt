package gs.calendar.appointments.frontend.imports

import react.RClass
import react.RProps

external interface Moment : RClass<RProps>

@JsModule("moment")
external val moment: Moment
