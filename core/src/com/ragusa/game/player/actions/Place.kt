package com.ragusa.game.player.actions

import com.ragusa.game.TileGrid
import com.ragusa.game.player.Robot
import com.ragusa.game.tiles.Tile
import com.ragusa.game.utility.plus

class Place(robot: Robot) : UndoableAction(robot) {

    override fun doAction(tileGrid: TileGrid): Boolean {
        if (!robot.isOn) return false

        val position = robot.position + dirToVec[robot.direction]!!

        if (tileGrid[position] == null && robot.hand != null) {
            tileGrid[position] = robot.hand!!
            robot.hand = null
            return true
        }
        return false
    }

    override fun undoAction(tileGrid: TileGrid) {
        val position = robot.position + dirToVec[robot.direction]!!
        robot.hand = tileGrid[position]
        tileGrid.removeAt(position)
    }

}