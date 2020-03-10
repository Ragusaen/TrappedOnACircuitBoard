package com.ragusa.game.tiles

import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Direction
import com.ragusa.game.IRenderable

abstract class TileAble: IRenderable {
    companion object {
        const val tileSize = 64f
    }

    open var position: Vector2 = Vector2.Zero

    open var direction: Direction = Direction.NORTH
}