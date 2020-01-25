package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.Direction

class TileBend : SimpleTile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH), TilePort(Direction.EAST))

    override val stateTextures: Map<Int, Texture> = mapOf(
        0b00 to Assets.tiles.bend.state_00,
        0b11 to Assets.tiles.bend.state_11
    ).entries.map { Pair(it.key, Assets.manager.get(it.value)) }.toMap()

}