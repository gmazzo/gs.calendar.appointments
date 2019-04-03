package material_ui.core.styles

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

private val muiThemeProvider = module.MuiThemeProvider.unsafeCast<MuiThemeProvider>()

fun RBuilder.muiThemeProvider(
    theme: Theme,
    handler: (RHandler<MuiThemeProvider.Props>) = {}
) = muiThemeProvider {
    attrs.theme = theme

    handler(this)
}

abstract external class MuiThemeProvider : RClass<MuiThemeProvider.Props> {

    interface Props : RProps {
        var theme: Theme
    }

}
