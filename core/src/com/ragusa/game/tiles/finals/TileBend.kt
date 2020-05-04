package com.ragusa.game.tiles.finals

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.tiles.Direction
import com.ragusa.game.tiles.SimpleTile
import com.ragusa.game.tiles.TilePort
import com.ragusa.game.tiles.initTextureStates

class TileBend : SimpleTile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH), TilePort(Direction.EAST))

    override val wireStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            0b00 to Assets.textures.tiles.bend.state_00,
            0b11 to Assets.textures.tiles.bend.state_11
    ))

}