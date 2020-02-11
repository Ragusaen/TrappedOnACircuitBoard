package com.ragusa.game.tiles

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.Direction

class TilePlus : SimpleTile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH), TilePort(Direction.EAST), TilePort(Direction.SOUTH), TilePort(Direction.WEST))

    override val wireStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
        0b0000 to Assets.tiles.plus.state_0000,
        0b1111 to Assets.tiles.plus.state_1111
    ))

}