package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.Direction

class TilePlus : SimpleTile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH), TilePort(Direction.EAST), TilePort(Direction.SOUTH), TilePort(Direction.WEST))

    override val stateTextures: Map<Int, Texture> = mapOf(
        0b0000 to Assets.tiles.plus.state_0000,
        0b1111 to Assets.tiles.plus.state_1111
    ).entries.map { Pair(it.key, Assets.manager.get(it.value)) }.toMap()

}