package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.Direction

class TileStraight: SimpleTile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH), TilePort(Direction.SOUTH))

    override val wireStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            0b00 to Assets.tiles.straight.state_00,
            0b11 to Assets.tiles.straight.state_11
    ))
}