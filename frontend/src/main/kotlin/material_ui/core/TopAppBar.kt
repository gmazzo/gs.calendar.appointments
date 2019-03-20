@file:JsModule("@material-ui/core")

package material_ui.core

import react.RClass
import react.RProps
import react.ReactElement

external interface TopAppBarProps : RProps {
    var title: String
    var navigationIcon: (() -> ReactElement)?
    var actionItems: (() -> List<ReactElement>)?
}

@JsName("TopAppBar")
external val TopAppBar: RClass<TopAppBarProps>
