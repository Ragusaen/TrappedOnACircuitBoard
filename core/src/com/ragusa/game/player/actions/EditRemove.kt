package com.ragusa.game.player.actions

import com.badlogic.gdx.math.Vector2
import com.ragusa.game.player.Robot
import com.ragusa.game.tiles.Tile
import com.ragusa.game.tiles.TileGrid
import com.ragusa.game.utility.plus

class EditRemove(robot: Robot) : UndoableAction(robot) {
    var previousTile: Tile? = null
    var targetPos: Vector2? = null

    override fun doAction(tileGrid: TileGrid): Boolean {
        targetPos = robot.position + dirToVec[robot.direction]!!

        previousTile = tileGrid[targetPos!!]

        // Cannot remove nothing
        if (previousTile == null) return false

        tileGrid.removeAt(targetPos!!)
        return true
    }

    override fun undoAction(tileGrid: TileGrid) {
        tileGrid[targetPos!!] = previousTile!!
    }
}