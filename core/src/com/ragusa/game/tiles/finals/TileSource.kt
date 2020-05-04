package com.ragusa.game.tiles.finals

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.tiles.*

class TileSource : WiredTile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH))

    override val wireStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            0b0 to Assets.textures.tiles.source.state_1,
            0b1 to Assets.textures.tiles.source.state_1
    ))

    override fun updateInternalState() {
        ports[0].state = PortState.OUT
    }
}
