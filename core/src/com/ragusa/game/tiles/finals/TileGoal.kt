package com.ragusa.game.tiles.finals

import com.badlogic.gdx.graphics.Texture
import com.ragusa.game.Assets
import com.ragusa.game.tiles.Direction
import com.ragusa.game.tiles.SimpleTile
import com.ragusa.game.tiles.TilePort
import com.ragusa.game.tiles.initTextureStates

class TileGoal : SimpleTile() {
    override val ports: Array<TilePort> = arrayOf(TilePort(Direction.NORTH))

    override val wireStateTextures: Map<Int, Texture> = initTextureStates(mapOf(
            0 to Assets.tiles.goal.state_0,
            1 to Assets.tiles.goal.state_1
    ))
}