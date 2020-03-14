package com.ragusa.game.tiles

// At tile in which all the ports are connected in a straightforward manner (e.g. Straight, Bend, Split)
abstract class SimpleTile(isLocked: Boolean, isCoated: Boolean) : WiredTile(isLocked, isCoated) {
    override fun updateInternalState() {
        if (ports.any { it.state == PortState.IN }) {
            // Set all OFF ports to OUT; do not overwrite IN
            for (port in ports) {
                if (port.state == PortState.OFF)
                    port.state = PortState.OUT
            }
        } else {
            for (port in ports) {
                if (port.state == PortState.OUT)
                    port.state = PortState.OFF
            }
        }

    }
}