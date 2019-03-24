package material_ui.core

enum class AppBarPosition(val value: String) {
    FIXED("fixed"),
    ABSOLUTE("absolute"),
    STICKY("sticky"),
    STATIC("static"),
    RELATIVE("relative")
}

var AppBar.Props.position
    get() = AppBarPosition.valueOf(positionValue.toUpperCase())
    set(value) {
        positionValue = value.name.toLowerCase()
    }
