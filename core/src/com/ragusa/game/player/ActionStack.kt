package com.ragusa.game.player

import com.ragusa.game.tiles.TileGrid
import com.ragusa.game.player.actions.UndoableAction

class ActionStack(val tileGrid: TileGrid) {
    private val stack = mutableListOf<UndoableAction>()

    fun doAction(action: UndoableAction): Boolean {
        // Do action, but only add it if it was successful
        if (action.doAction(tileGrid)) {
            stack.add(action)
            return true
        }
        return false
    }

    fun undoAction() {
        if (stack.isNotEmpty()) {
            stack.last().undoAction(tileGrid)
            stack.removeAt(stack.size - 1)
        }
        // Give some feedback to the user
    }
}