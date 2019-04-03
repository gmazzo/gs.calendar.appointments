package material_ui.core.styles

fun createMuiTheme(): Theme = module.createMuiTheme().unsafeCast<Theme>()

external interface Theme {

    val spacing: Spacing

    interface Spacing {
        val unit: Int
    }

}
