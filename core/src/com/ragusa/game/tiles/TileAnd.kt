package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.initTextureStates

class TileAnd(isLocked: Boolean, isCoated: Boolean) : TernaryGateTile(isLocked, isCoated) {

    override val gateStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            DEFAULT_TEXTURE to Assets.tiles.and.state_000,
            0b111 to Assets.tiles.and.state_000
    ))

    override fun updateInternalState() {
        if (ports[1].isOn() && ports[2].isOn())
            ports[0].state = PortState.OUT;
        else if (ports[0].state == PortState.OUT)
            ports[0].state = PortState.OFF;
    }
}