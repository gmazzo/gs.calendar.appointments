package app

import react.*
import react.dom.*
import button.*

fun RBuilder.app() {
    div {
        +"Hello World!"
    }
    Button {
        //attrs.asDynamic.className = "submit-button"
        +"Submit"
    }
}
