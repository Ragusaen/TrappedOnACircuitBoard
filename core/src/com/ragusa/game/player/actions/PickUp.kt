package com.ragusa.game.player.actions

import com.ragusa.game.tiles.TileGrid
import com.ragusa.game.player.Robot
import com.ragusa.game.tiles.PortState
import com.ragusa.game.tiles.Tile
import com.ragusa.game.tiles.WiredTile
import com.ragusa.game.utility.plus

class PickUp(robot: Robot) : UndoableAction(robot) {
    var tile: Tile? = null

    override fun doAction(tileGrid: TileGrid): Boolean {
        if (!robot.isOn) return false

        val position = robot.position + dirToVec[robot.direction]!!

        tile = tileGrid[position]
        if (tile != null && robot.hand == null && !tile!!.isLocked) {
            // Disconnect all of its ports
            if (tile is WiredTile) {
                (tile as WiredTile).ports.forEach {it.connectedPort?.connectedPort = null; it.connectedPort = null; it.state = PortState.OFF}
                (tile as WiredTile).updateInternalState()
            }

            tileGrid.removeAt(position)
            robot.hand = tile
            return true
        }
        return false
    }

    override fun undoAction(tileGrid: TileGrid) {
        val position = robot.position + dirToVec[robot.direction]!!
        tileGrid[position] = tile!!
        robot.hand = null
    }

}