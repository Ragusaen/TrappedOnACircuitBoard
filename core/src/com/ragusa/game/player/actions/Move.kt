package com.ragusa.game.player.actions

import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Direction
import com.ragusa.game.TileGrid
import com.ragusa.game.player.Robot
import com.ragusa.game.utility.*

class Move(val direction: Direction, robot: Robot) : UndoableAction(robot) {
    var prevDirection: Direction? = null

    override fun doAction(tileGrid: TileGrid): Boolean {
        val movement = dirToVec[direction]!!
        if (robot.isOn && tileGrid[robot.position + movement] != null) {
            prevDirection = robot.direction
            robot.direction = direction
            robot.position += movement
            return true
        }
        return false
    }

    override fun undoAction(tileGrid: TileGrid) {
        robot.position -= dirToVec[direction]!!
        robot.direction = prevDirection!!
    }

}