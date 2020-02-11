package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.Direction

class TileOr : TernaryGateTile() {
    override val gateStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            DEFAULT_TEXTURE to Assets.tiles.or.state_000,
            0b101 to Assets.tiles.or.state_101,
            0b110 to Assets.tiles.or.state_110,
            0b111 to Assets.tiles.or.state_111
    ))

    override fun updateInternalState() {
        if (ports[1].isOn() || ports[2].isOn())
            delayedPorts[0].state = PortState.OUT
        else if (ports[0].state == PortState.OUT)
            delayedPorts[0].state = PortState.OFF
    }

}