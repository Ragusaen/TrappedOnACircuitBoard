package com.ragusa.game.level

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ragusa.game.IRenderable
import com.ragusa.game.tiles.TileGrid
import com.ragusa.game.player.Robot


class Level(var name: String = "Default Name",
            var author: String = "Unknown",
            val tileGrid: TileGrid,
            val robot: Robot) : IRenderable {

    companion object {
        fun emptyLevel(): Level {
            val robot = Robot()
            return Level("Unnamed", "Unknown", TileGrid(robot), robot)
        }
    }

    override fun render(batch: SpriteBatch, relativeTo: Vector2) {
        tileGrid.render(batch, relativeTo)
        robot.render(batch, relativeTo)
    }

}