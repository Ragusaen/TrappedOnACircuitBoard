package com.ragusa.game.player.actions

import com.ragusa.game.Direction
import com.ragusa.game.TileGrid
import com.ragusa.game.player.Robot

class RotateHand(robot: Robot) : UndoableAction(robot) {
    override fun doAction(tileGrid: TileGrid): Boolean {
        if (robot.hand != null) {
            robot.hand!!.direction += Direction.EAST
            return true
        }
        return false
    }

    override fun undoAction(tileGrid: TileGrid) {
        robot.hand!!.direction += Direction.WEST
    }

}