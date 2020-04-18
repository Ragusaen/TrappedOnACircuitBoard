package com.ragusa.game.tiles.finals

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.tiles.Direction
import com.ragusa.game.tiles.SimpleTile
import com.ragusa.game.tiles.TilePort
import com.ragusa.game.tiles.initTextureStates

class TileSplit : SimpleTile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH), TilePort(Direction.EAST), TilePort(Direction.SOUTH))

    override val wireStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            0b000 to Assets.tiles.split.state_000,
            0b111 to Assets.tiles.split.state_111
    ))

}