package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.Direction
import com.ragusa.game.initTextureStates

class TileXor(isLocked: Boolean, isCoated: Boolean) : TernaryGateTile(isLocked, isCoated) {
    override val gateStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            DEFAULT_TEXTURE to Assets.tiles.xor.state_000,
            0b101 to Assets.tiles.xor.state_101,
            0b110 to Assets.tiles.xor.state_110
    ))

    override fun updateInternalState() {
        if (ports[1].isOn() != ports[2].isOn())
            ports[0].state = PortState.OUT
        else if (ports[0].state == PortState.OUT)
            ports[0].state = PortState.OFF
    }
}