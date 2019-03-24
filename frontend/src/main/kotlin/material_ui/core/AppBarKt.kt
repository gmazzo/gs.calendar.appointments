package material_ui.core

enum class AppBarPosition { FIXED, ABSOLUTE, STICKY, STATIC, RELATIVE }

var AppBar.Props.position
    get() = AppBarPosition.valueOf(positionValue.toUpperCase())
    set(value) {
        positionValue = value.name.toLowerCase()
    }
