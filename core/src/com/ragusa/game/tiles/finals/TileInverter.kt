package com.ragusa.game.tiles.finals

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.tiles.BinaryGateTile
import com.ragusa.game.tiles.PortState
import com.ragusa.game.tiles.initTextureStates

class TileInverter : BinaryGateTile() {
    override val gateStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            DEFAULT_TEXTURE to Assets.tiles.inverter.state_01,
            0b10 to Assets.tiles.inverter.state_10
    ))

    override fun updateInternalState() {
        if (ports[1].state == PortState.OFF)
            ports[0].state = PortState.OUT
        else if (ports[0].state == PortState.OUT)
            ports[0].state = PortState.OFF
    }

}