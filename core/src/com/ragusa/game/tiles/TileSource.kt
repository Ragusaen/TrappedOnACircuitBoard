package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.Direction

class TileSource : Tile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH))

    override val stateTextures: Map<Int, Texture> = mapOf(
            0b1 to Assets.tiles.source.state_1
    ).entries.map { Pair(it.key, Assets.manager.get(it.value)) }.toMap()


    override fun updatePorts() {
        ports[0].state = PortState.OUT
    }
}
