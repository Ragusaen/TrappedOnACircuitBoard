package com.ragusa.game.player.actions

import com.badlogic.gdx.math.Vector2
import com.ragusa.game.Direction
import com.ragusa.game.TileGrid
import com.ragusa.game.player.Robot

abstract class UndoableAction(val robot: Robot) {
    companion object {
        val dirToVec = mapOf(
                Direction.NORTH to Vector2(0f, 1f),
                Direction.SOUTH to Vector2(0f, -1f),
                Direction.EAST to Vector2(1f, 0f),
                Direction.WEST to Vector2(-1f, 0f)
        )
    }

    abstract fun doAction(tileGrid: TileGrid): Boolean
    abstract fun undoAction(tileGrid: TileGrid)
}