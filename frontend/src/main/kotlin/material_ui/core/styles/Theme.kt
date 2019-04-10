package material_ui.core.styles

fun createMuiTheme(): Theme = module.createMuiTheme().unsafeCast<Theme>()

external interface Theme {

    val spacing: Spacing

    val palette: Palette

    interface Spacing {
        val unit: Int
    }

    interface Palette {
        val divider: String
    }

}
