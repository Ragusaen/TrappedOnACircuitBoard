package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.Direction
import com.ragusa.game.initTextureStates

class TileSource(isLocked: Boolean, isCoated: Boolean) : WiredTile(isLocked, isCoated) {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH))

    override val wireStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            0b1 to Assets.tiles.source.state_1
    ))

    override fun updateInternalState() {
        ports[0].state = PortState.OUT
    }
}
