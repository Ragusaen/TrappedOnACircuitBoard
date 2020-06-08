package com.ragusa.game.level

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.IRenderable
import com.ragusa.game.tiles.TileGrid
import com.ragusa.game.player.Robot


class Level(val name: String = "Default Name",
            val author: String = "Unknown",
            val tileGrid: TileGrid,
            val robot: Robot) : IRenderable {

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        tileGrid.render(batch, relativeTo)
        robot.render(batch, relativeTo)
    }

}