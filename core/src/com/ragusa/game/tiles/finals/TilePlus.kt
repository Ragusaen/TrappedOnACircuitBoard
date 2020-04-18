package com.ragusa.game.tiles.finals

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.tiles.Direction
import com.ragusa.game.tiles.SimpleTile
import com.ragusa.game.tiles.TilePort
import com.ragusa.game.tiles.initTextureStates

class TilePlus : SimpleTile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH), TilePort(Direction.EAST), TilePort(Direction.SOUTH), TilePort(Direction.WEST))

    override val wireStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            0b0000 to Assets.tiles.plus.state_0000,
            0b1111 to Assets.tiles.plus.state_1111
    ))

}