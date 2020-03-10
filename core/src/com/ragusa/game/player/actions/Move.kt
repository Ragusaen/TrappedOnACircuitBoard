package com.ragusa.game.player.actions

import com.badlogic.gdx.math.Vector2
import com.ragusa.game.TileGrid
import com.ragusa.game.player.Robot
import com.ragusa.game.utility.*

class Move(val movement: Vector2, robot: Robot) : UndoableAction(robot) {

    companion object {
        val LEFT = Vector2(-1f, 0f)
        val RIGHT = Vector2(1f, 0f)
        val UP = Vector2(0f, 1f)
        val DOWN = Vector2(0f, -1f)
    }

    override fun doAction(tileGrid: TileGrid): Boolean {
        if (robot.isOn && tileGrid[robot.position + movement] != null) {
            robot.position += movement
            return true
        }
        return false
    }

    override fun undoAction() {
        robot.position -= movement
    }

}