package gs.calendar.appointments.frontend

import material_ui.core.uiCssBaseline
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    window.onload = {
        render(document.getElementById("root")!!) {
            uiCssBaseline {
            }
            app()
        }
    }
}
