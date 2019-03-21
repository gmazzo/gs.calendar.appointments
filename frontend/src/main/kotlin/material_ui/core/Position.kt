package material_ui.core

enum class Position {

    FIXED, ABSOLUTE, STICKY, STATIC, RELATIVE;

    val value
        get() = name.toLowerCase()

}
