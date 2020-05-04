package com.ragusa.game.tiles.finals

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.tiles.PortState
import com.ragusa.game.tiles.TernaryGateTile
import com.ragusa.game.tiles.initTextureStates

class TileOr : TernaryGateTile() {
    override val gateStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            DEFAULT_TEXTURE to Assets.textures.tiles.or.state_000,
            0b101 to Assets.textures.tiles.or.state_101,
            0b110 to Assets.textures.tiles.or.state_110,
            0b111 to Assets.textures.tiles.or.state_111
    ))

    override fun updateInternalState() {
        if (ports[1].isOn() || ports[2].isOn())
            ports[0].state = PortState.OUT
        else if (ports[0].state == PortState.OUT)
            ports[0].state = PortState.OFF
    }

}