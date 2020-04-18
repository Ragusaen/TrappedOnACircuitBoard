package com.ragusa.game.tiles

import com.ragusa.game.IRenderable

abstract class TileAble: IRenderable {
    companion object {
        const val tileSize = 64f
    }

    open var direction: Direction = Direction.NORTH
}