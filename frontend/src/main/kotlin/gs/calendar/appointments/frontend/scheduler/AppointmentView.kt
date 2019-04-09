package gs.calendar.appointments.frontend.scheduler

import react.RBuilder
import react.RComponent
import react.RState
import react_big_calendar.BigCalendar

class AppointmentView : RComponent<AppointmentView.Props, RState>() {

    override fun RBuilder.render() {
        props.children()
    }

    interface Props : BigCalendar.EventProps

}
