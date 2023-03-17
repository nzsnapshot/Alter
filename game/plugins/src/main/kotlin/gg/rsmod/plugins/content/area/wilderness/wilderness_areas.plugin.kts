package gg.rsmod.plugins.content.area.wilderness
val CHECK_TIMER = TimerKey()
val WAS_IN_WILD = AttributeKey<Boolean>()

val INTERFACE_ID = 90

val SKULL_CROSSOUT_COMPONENT = 63
val SKULL_COMPONENT = 62

on_login {
    player.timers[CHECK_TIMER] = 1
}

on_timer(CHECK_TIMER) {
    player.timers[CHECK_TIMER] = 1

    if (in_wilderness(player.tile)) {
        val inWild = player.attr[WAS_IN_WILD] ?: false
        if (!inWild) {
            set_in_wild(player, true)
        }
    } else {
        val inWild = player.attr[WAS_IN_WILD] ?: false
        if (inWild) {
            set_in_wild(player, false)
        }
    }
}

fun set_in_wild(player: Player, inWilderness: Boolean) {
    player.attr[WAS_IN_WILD] = inWilderness
    if (inWilderness) {
        player.openInterface(dest = InterfaceDestination.OVERLAY, interfaceId = INTERFACE_ID)
        player.sendOption("Attack", 2)
    } else {
        player.closeInterface(dest = InterfaceDestination.OVERLAY)
        player.removeOption(2)
    }
//    player.setComponentHidden(interfaceId = INTERFACE_ID, component = SKULL_CROSSOUT_COMPONENT, hidden = inWilderness)
}

fun in_wilderness(tile: Tile): Boolean = tile.getWildernessLevel() > 0