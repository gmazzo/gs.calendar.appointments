@file:JsModule("@material-ui/core")

package material_ui.core

import org.w3c.dom.events.MouseEvent
import react.RClass
import react.RProps

abstract external class Drawer : RClass<Drawer.Props> {

    interface Props : RProps {

        @JsName("anchor")
        var anchorValue: String

        var onClose: (MouseEvent) -> Unit

        var open: Boolean

        @JsName("variant")
        var variantValue: String

    }

}

@JsName("Drawer")
external val drawer: Drawer
