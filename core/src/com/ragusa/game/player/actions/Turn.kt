package com.ragusa.game.player.actions

import com.ragusa.game.tiles.Direction
import com.ragusa.game.tiles.TileGrid
import com.ragusa.game.player.Robot

class Turn(val direction: Direction, robot: Robot) : UndoableAction(robot) {
    private val prevDirection = robot.direction

    override fun doAction(tileGrid: TileGrid): Boolean {
        if (!robot.isOn) return false

        if (robot.direction != direction) {
            robot.direction = direction
            return true
        }
        return false
    }

    override fun undoAction(tileGrid: TileGrid) {
        robot.direction = prevDirection
    }

}