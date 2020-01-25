package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.Direction

class TileSplit : SimpleTile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH), TilePort(Direction.EAST), TilePort(Direction.SOUTH))

    override val stateTextures: Map<Int, Texture> = mapOf(
        0b000 to Assets.tiles.split.state_000,
        0b111 to Assets.tiles.split.state_111
    ).entries.map { Pair(it.key, Assets.manager.get(it.value)) }.toMap()

}