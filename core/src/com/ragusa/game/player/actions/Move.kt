package com.ragusa.game.player.actions

import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Direction
import com.ragusa.game.TileGrid
import com.ragusa.game.player.Robot
import com.ragusa.game.tiles.WiredTile
import com.ragusa.game.utility.*

class Move(val direction: Direction, robot: Robot) : UndoableAction(robot) {
    var prevDirection: Direction? = null

    override fun doAction(tileGrid: TileGrid): Boolean {
        if (!robot.isOn) return false

        // Check if the current tile has a port in the moving direction that is turned on
        val currentTile = tileGrid[robot.position]
        if (currentTile is WiredTile && currentTile.ports.any {it.direction + currentTile.direction == direction && it.isOn()}) {

            // Check if the tile to move to has a port in the incoming direction
            val movement = dirToVec[direction]!!
            val moveToTile = tileGrid[robot.position + movement]
            if (moveToTile is WiredTile && moveToTile.ports.any {it.direction + moveToTile.direction == direction + Direction.SOUTH && it.isOn()}) {
                prevDirection = robot.direction
                robot.direction = direction
                robot.position += movement
                return true
            }
        }
        return false
    }

    override fun undoAction(tileGrid: TileGrid) {
        robot.position -= dirToVec[direction]!!
        robot.direction = prevDirection!!
    }

}