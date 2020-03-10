package com.ragusa.game.player.actions

import com.ragusa.game.TileGrid
import com.ragusa.game.player.Robot

abstract class UndoableAction(val robot: Robot) {
    abstract fun doAction(tileGrid: TileGrid): Boolean
    abstract fun undoAction()
}